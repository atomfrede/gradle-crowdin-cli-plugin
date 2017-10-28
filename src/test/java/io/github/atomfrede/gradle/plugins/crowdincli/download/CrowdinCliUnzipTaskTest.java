package io.github.atomfrede.gradle.plugins.crowdincli.download;

import io.github.atomfrede.gradle.plugins.crowdincli.task.download.CrowdinCliDownloadTask;
import io.github.atomfrede.gradle.plugins.crowdincli.task.download.CrowdinCliUnzipTask;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CrowdinCliUnzipTaskTest {

    @Test
    public void crowdin_cli_unzip_task_can_be_created() {

        Project project = ProjectBuilder.builder().build();
        CrowdinCliDownloadTask crowdinCliDownloadTask = project.getTasks().create(CrowdinCliDownloadTask.TASK_NAME, CrowdinCliDownloadTask.class);

        CrowdinCliUnzipTask crowdinCliUnzipTask = project.getTasks().create(CrowdinCliUnzipTask.TASK_NAME, CrowdinCliUnzipTask.class);

        assertThat(crowdinCliUnzipTask.getDestinationDir().getAbsolutePath())
            .endsWith("/gradle/crowdin-cli");

        assertThat(crowdinCliUnzipTask.getIncludes())
            .hasSize(1)
            .as("Only jar file is extracted.")
            .containsOnly("**/*.jar");
    }

    @Test
    public void crowdin_cli_unzip_requires_download_task() {

        Project project = ProjectBuilder.builder().build();

        assertThatThrownBy(() -> project.getTasks().create(CrowdinCliUnzipTask.TASK_NAME, CrowdinCliUnzipTask.class))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Could not create task of type 'CrowdinCliUnzipTask'.");
    }
}
