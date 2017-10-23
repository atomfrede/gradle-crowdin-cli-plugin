package io.github.atomfrede.gradle.plugins.crowdincli;

import io.github.atomfrede.gradle.plugins.crowdincli.task.CrowdinCliDownloadTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.Copy;

import java.util.Collections;

public class CrowdinCliPlugin implements Plugin<Project> {

    public static final String GROUP = "Crowdin";
    @Override
    public void apply(Project project) {

        CrowdinCliDownloadTask downloadTask = createCrowdinCliDownloadTask(project);
        unzipCrowdinCli(project, downloadTask);

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
