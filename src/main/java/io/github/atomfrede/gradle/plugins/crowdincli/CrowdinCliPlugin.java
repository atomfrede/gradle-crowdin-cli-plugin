package io.github.atomfrede.gradle.plugins.crowdincli;

import io.github.atomfrede.gradle.plugins.crowdincli.task.CrowdinCliDownloadTask;
import io.github.atomfrede.gradle.plugins.crowdincli.task.UnzipCrowdinCliTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.FileTree;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.Exec;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrowdinCliPlugin implements Plugin<Project> {

    public static final String GROUP = "Crowdin";
    @Override
    public void apply(Project project) {

        CrowdinCliDownloadTask downloadTask = createCrowdinCliDownloadTask(project);

        UnzipCrowdinCliTask unzipCrowdinCliTask = project.getTasks().create(UnzipCrowdinCliTask.NAME, UnzipCrowdinCliTask.class);

        String crowdinCliExecutable = getCrowdinCli(project, unzipCrowdinCliTask);

        project.getTasks().create("crowdinHelp", Exec.class, exec -> {
            exec.commandLine("java", "-jar", crowdinCliExecutable, "--help");
            exec.setGroup(GROUP);
            exec.setDescription("Execute and display the crowdin --help output");
            exec.dependsOn(unzipCrowdinCliTask);
        });

    }

    // TODO this works not for new, empty project as this is resolved on configuration time
    private String getCrowdinCli(Project project, UnzipCrowdinCliTask unzipTask) {

        FileTree tree = project.fileTree(unzipTask.getDestinationDir(), files -> {
            files.include("**/crowdin-cli.jar");
        });

        List<File> files = new ArrayList<>();
        files.addAll(tree.getFiles());

        if (files.size() >0) {
            return files.get(0).getAbsolutePath();
        }

        return "";


    }

    private CrowdinCliDownloadTask createCrowdinCliDownloadTask(Project project) {

        CrowdinCliDownloadTask crowdinCliDownloadTask = project.getTasks().create(CrowdinCliDownloadTask.TASK_NAME, CrowdinCliDownloadTask.class);
        crowdinCliDownloadTask.setGroup(GROUP);
        crowdinCliDownloadTask.setDescription(CrowdinCliDownloadTask.DESCRIPTION);

        return crowdinCliDownloadTask;
    }
}
