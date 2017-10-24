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


        project.getTasks().create("crowdinHelp", Exec.class, exec -> {

            exec.commandLine("java", "-jar", "./gradle/crowdin-cli/crowdin-cli.jar", "--help");
            exec.setGroup(GROUP);
            exec.setDescription("Execute and display the crowdin --help output");
            exec.dependsOn(unzipCrowdinCliTask);
        });

    }

    private CrowdinCliDownloadTask createCrowdinCliDownloadTask(Project project) {

        CrowdinCliDownloadTask crowdinCliDownloadTask = project.getTasks().create(CrowdinCliDownloadTask.TASK_NAME, CrowdinCliDownloadTask.class);
        crowdinCliDownloadTask.setGroup(GROUP);
        crowdinCliDownloadTask.setDescription(CrowdinCliDownloadTask.DESCRIPTION);

        return crowdinCliDownloadTask;
    }
}
