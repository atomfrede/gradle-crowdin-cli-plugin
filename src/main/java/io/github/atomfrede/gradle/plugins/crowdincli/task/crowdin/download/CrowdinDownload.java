package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.download;

import groovy.lang.Closure;
import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinSync;
import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git.GitConfig;
import org.gradle.api.Action;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;

import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.gradle.api.file.ConfigurableFileTree;
import org.gradle.api.file.FileTree;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Preconfigured @{@link io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinCli} task to download translation.
 */
public class CrowdinDownload extends CrowdinSync implements CrowdinDownloadSpec {

    protected GitConfig gitConfig = new GitConfig();
    protected String language = "all";

    public CrowdinDownload() {

        this.command = new ArrayList<>();
        this.command.add("download");

    }

    @Input
    @Optional
    @Override
    public void language(String language) {
        this.language = language;
    }

    @Input
    @Optional
    @Override
    public void git(Closure c) {
        org.gradle.util.ConfigureUtil.configure(c, gitConfig);
    }

    @Input
    @Optional
    @Override
    public void git(Action<? super GitConfig> action) {
        action.execute(gitConfig);
    }

    @TaskAction
    public void exec() {

        if (isDryRun()) {
            this.command.add("--dryrun");
        }

        super.exec();

        if (gitConfig.isEnabled()) {
            try {
                commitTranslationFiles();
            } catch (IOException | GitAPIException e) {
                e.printStackTrace();
            }
        }
    }

    protected Set<File> getTranslationFiles() throws FileNotFoundException {
        final Yaml yamlFile = new Yaml();
        final Object yamlData = yamlFile.load(new FileReader(getConfigFile()));
        getLogger().debug("Yaml data:" + yamlData);
        final Map<String, Object> configMap = (Map<String, Object>) yamlData;

        final List<Map<String, String>> translationFileConfigs = (List<Map<String, String>>) configMap.get("files");


        File projectPath = getProject().getProjectDir();
        File basePath = projectPath;
        if (configMap.containsKey("base_path")) {
            basePath = new File(projectPath, (String) configMap.get("base_path"));
        }

        FileTree translationFiles = null;

        for (Map<String, String> fileConfig : translationFileConfigs) {
            String translationFile = fileConfig.get("translation").replaceAll("%.*%", "*");
            getLogger().debug("Translation file wildcard: " + translationFile);

            ConfigurableFileTree cft = getProject().fileTree(basePath);
            cft.include(translationFile);

            if (translationFiles == null) {
                translationFiles = cft;
            } else {
                translationFiles = translationFiles.plus(cft);
            }
        }

        return translationFiles.getFiles();
    }

    protected void commitTranslationFiles () throws IOException, GitAPIException {
        boolean doCommit = false;
        Path projectPath = Paths.get(getProject().getProjectDir().toURI());
        File gitDir = new File(getProject().getProjectDir(), ".git");
        Repository gitRepo = new FileRepositoryBuilder().setGitDir(gitDir).build();

        org.eclipse.jgit.api.Git git = new org.eclipse.jgit.api.Git(gitRepo);
        Status gitStatus = git.status().call();

        if (!gitStatus.isClean()) {
            Set<File> translationFiles = getTranslationFiles();

            Set<String> unstaged = gitStatus.getModified();
            Set<String> untracked = gitStatus.getUntracked();

            for (File transFile : translationFiles) {
                // We need a string representation for checking against the git status
                String filePattern = projectPath.relativize(Paths.get(transFile.toURI())).toString();

                if (unstaged.contains(filePattern) || untracked.contains(filePattern)) {
                    // Add only modified or untracked translation files
                    git.add().addFilepattern(filePattern).call();
                    getLogger().lifecycle("Staging translation file: " + filePattern);
                    doCommit = true; // We got a translation file to commit! Yay!
                }
            }

            if (doCommit) {
                String message = gitConfig.getCommitMessage();
                String authorName = gitConfig.getAuthorName();
                String authorEmail = gitConfig.getAuthorEmail();

                if (authorEmail.isEmpty() || authorName.isEmpty()) {
                    git.commit().setMessage(message).call();
                } else {
                    git.commit().setMessage(message).setAuthor(authorName, authorEmail).call();
                }
            } else {
                // Edge case: git status unclean but no new or modified translation files found
                getLogger().lifecycle("No translation files to commit. (GitConfig unclean though)");
            }
        } else {
            // Edge case: Crowdin Download produced no changes whatsoever.
            getLogger().lifecycle("GitConfig status is clean, nothing to commit.");
        }

    }
}
