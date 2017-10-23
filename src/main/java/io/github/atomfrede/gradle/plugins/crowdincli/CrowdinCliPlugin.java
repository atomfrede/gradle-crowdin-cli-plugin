package io.github.atomfrede.gradle.plugins.crowdincli;

import io.github.atomfrede.gradle.plugins.crowdincli.task.CrowdinCliDownloadTask;
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
        Copy unzipTask = unzipCrowdinCli(project, downloadTask);

        File crowdinCliExecutable = getCrowdinCli(project, unzipTask);

        project.getTasks().create("crowdinHelp", Exec.class, exec -> {
            exec.commandLine("java", "-jar", crowdinCliExecutable.getAbsolutePath(), "--help");
            exec.setGroup(GROUP);
            exec.setDescription("Execute and display the crowdin --help output");
            exec.dependsOn(unzipTask);
        });

    }

    private File getCrowdinCli(Project project, Copy unzipTask) {

        FileTree tree = project.fileTree(unzipTask.getDestinationDir(), files -> {
            files.include("**/crowdin-cli.jar");
        });

        List<File> files = new ArrayList<>();
        files.addAll(tree.getFiles());

        return files.get(0);

    }

    private Copy unzipCrowdinCli(Project project, CrowdinCliDownloadTask crowdinCliDownloadTask) {

        return project.getTasks().create("unzipCrowdinCli", Copy.class, copy -> {

            copy.dependsOn(Collections.singletonList(crowdinCliDownloadTask));
            copy.setGroup(GROUP);
            copy.setDescription("Unzip the crowdin cli");

            copy.from(project.zipTree(crowdinCliDownloadTask.getDest()));
            copy.setDestinationDir(crowdinCliDownloadTask.getDest().getParentFile());
        });

    }

    private CrowdinCliDownloadTask createCrowdinCliDownloadTask(Project project) {

        CrowdinCliDownloadTask crowdinCliDownloadTask = project.getTasks().create(CrowdinCliDownloadTask.TASK_NAME, CrowdinCliDownloadTask.class);
        crowdinCliDownloadTask.setGroup(GROUP);
        crowdinCliDownloadTask.setDescription(CrowdinCliDownloadTask.DESCRIPTION);

        return crowdinCliDownloadTask;
    }
}
