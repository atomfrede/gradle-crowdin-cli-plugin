package io.github.atomfrede.gradle.plugins.crowdincli.download;

import io.github.atomfrede.gradle.plugins.crowdincli.task.download.CrowdinCliDownloadTask;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CrowdinCliDownloadTaskTest {

    @Test
    public void crowdin_cli_download_url_should_be_set_as_src() {

        Project project = ProjectBuilder.builder().build();
        CrowdinCliDownloadTask crowdinCliDownloadTask = project.getTasks().create(CrowdinCliDownloadTask.TASK_NAME, CrowdinCliDownloadTask.class);

        assertThat(crowdinCliDownloadTask.getSrc().toString())
            .as("Correct crowdin cli download URL is set.")
            .isEqualTo("https://downloads.crowdin.com/cli/v2/crowdin-cli.zip");
    }
}
