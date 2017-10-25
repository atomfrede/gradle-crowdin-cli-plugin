package io.github.atomfrede.gradle.plugins.crowdincli;

import io.github.atomfrede.gradle.plugins.crowdincli.task.download.CrowdinCliDownloadTask;
import io.github.atomfrede.gradle.plugins.crowdincli.task.download.CrowdinCliUnzipTask;
import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinCliExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.Exec;

public class CrowdinCliPlugin implements Plugin<Project> {

    public static final String GROUP = "Crowdin";

    @Override
    public void apply(Project project) {

        CrowdinCliDownloadTask downloadTask = createCrowdinCliDownloadTask(project);

        CrowdinCliUnzipTask crowdinCliUnzipTask = project.getTasks().create(CrowdinCliUnzipTask.TASK_NAME, CrowdinCliUnzipTask.class);

        project.getExtensions().create("crowdinCli", CrowdinCliExtension.class, project);
    }

    private CrowdinCliDownloadTask createCrowdinCliDownloadTask(Project project) {

        CrowdinCliDownloadTask crowdinCliDownloadTask = project.getTasks().create(CrowdinCliDownloadTask.TASK_NAME, CrowdinCliDownloadTask.class);
        crowdinCliDownloadTask.setGroup(GROUP);
        crowdinCliDownloadTask.setDescription(CrowdinCliDownloadTask.DESCRIPTION);

        return crowdinCliDownloadTask;
    }
}
