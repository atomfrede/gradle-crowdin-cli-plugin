package io.github.atomfrede.gradle.plugins.crowdincli;

import io.github.atomfrede.gradle.plugins.crowdincli.task.CrowdinCliDownloadTask;
import io.github.atomfrede.gradle.plugins.crowdincli.task.UnzipCliTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.internal.file.archive.ZipFileTree;
import org.gradle.api.tasks.Copy;

import java.util.Collections;

public class CrowdinCliPlugin implements Plugin<Project> {

    public static final String GROUP = "Crowdin";
    @Override
    public void apply(Project project) {

        CrowdinCliDownloadTask downloadTask = createCrowdinCliDownloadTask(project);
        unzipCrowdinCli(project, downloadTask);

    }

    private void unzipCrowdinCli(Project project, CrowdinCliDownloadTask crowdinCliDownloadTask) {

        project.getTasks().create("unzipCli", Copy.class, copy -> {

            copy.dependsOn(Collections.singletonList(crowdinCliDownloadTask));
            copy.setGroup(GROUP);
            copy.setDescription("Unzip the crowdin cli");
            copy.setDestinationDir(crowdinCliDownloadTask.getDest());
        });

    }

    private CrowdinCliDownloadTask createCrowdinCliDownloadTask(Project project) {

        CrowdinCliDownloadTask crowdinCliDownloadTask = project.getTasks().create(CrowdinCliDownloadTask.TASK_NAME, CrowdinCliDownloadTask.class);
        crowdinCliDownloadTask.setGroup(GROUP);
        crowdinCliDownloadTask.setDescription(CrowdinCliDownloadTask.DESCRIPTION);

        return crowdinCliDownloadTask;
    }
}
