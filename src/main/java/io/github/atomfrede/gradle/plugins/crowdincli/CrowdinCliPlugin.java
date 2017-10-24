package io.github.atomfrede.gradle.plugins.crowdincli;

import io.github.atomfrede.gradle.plugins.crowdincli.task.cli.CrowdinCliDownloadTask;
import io.github.atomfrede.gradle.plugins.crowdincli.task.cli.CrowdinCliUnzipTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.Exec;

public class CrowdinCliPlugin implements Plugin<Project> {

    public static final String GROUP = "Crowdin";
    @Override
    public void apply(Project project) {

        CrowdinCliDownloadTask downloadTask = createCrowdinCliDownloadTask(project);

        CrowdinCliUnzipTask crowdinCliUnzipTask = project.getTasks().create(CrowdinCliUnzipTask.NAME, CrowdinCliUnzipTask.class);


        project.getTasks().create("crowdinHelp", Exec.class, exec -> {

            exec.commandLine("java", "-jar", "./gradle/crowdin-cli/crowdin-cli.jar", "--help");
            exec.setGroup(GROUP);
            exec.setDescription("Execute and display the crowdin --help output");
            exec.dependsOn(crowdinCliUnzipTask);
        });

        project.getTasks().create("crowdinLint", Exec.class, exec -> {

            exec.commandLine("java", "-jar", "./gradle/crowdin-cli/crowdin-cli.jar", "lint");
            exec.setGroup(GROUP);
            exec.setDescription("Lint your config file");
            exec.dependsOn(crowdinCliUnzipTask);
        });

    }

    private CrowdinCliDownloadTask createCrowdinCliDownloadTask(Project project) {

        CrowdinCliDownloadTask crowdinCliDownloadTask = project.getTasks().create(CrowdinCliDownloadTask.TASK_NAME, CrowdinCliDownloadTask.class);
        crowdinCliDownloadTask.setGroup(GROUP);
        crowdinCliDownloadTask.setDescription(CrowdinCliDownloadTask.DESCRIPTION);

        return crowdinCliDownloadTask;
    }
}
