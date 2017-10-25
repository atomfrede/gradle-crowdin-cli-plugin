package io.github.atomfrede.gradle.plugins.crowdincli;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinCliExtension;
import io.github.atomfrede.gradle.plugins.crowdincli.task.download.CrowdinCliDownloadTask;
import io.github.atomfrede.gradle.plugins.crowdincli.task.download.CrowdinCliUnzipTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CrowdinCliPlugin implements Plugin<Project> {

    public static final String GROUP = "Crowdin";

    @Override
    public void apply(Project project) {

        createCrowdinCliDownloadTask(project);

        project.getTasks().create(CrowdinCliUnzipTask.TASK_NAME, CrowdinCliUnzipTask.class);

        project.getExtensions().create("crowdinCli", CrowdinCliExtension.class, project);
    }

    private CrowdinCliDownloadTask createCrowdinCliDownloadTask(Project project) {

        CrowdinCliDownloadTask crowdinCliDownloadTask = project.getTasks().create(CrowdinCliDownloadTask.TASK_NAME, CrowdinCliDownloadTask.class);
        crowdinCliDownloadTask.setGroup(GROUP);
        crowdinCliDownloadTask.setDescription(CrowdinCliDownloadTask.DESCRIPTION);

        return crowdinCliDownloadTask;
    }
}
