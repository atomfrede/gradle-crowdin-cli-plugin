package io.github.atomfrede.gradle.plugins.crowdincli;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionalPluginTest {

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();

    private File buildFile;

    @Before
    public void setup() throws IOException {
        buildFile = testProjectDir.newFile("build.gradle");
    }

    @Test
    public void test_crowdin_cli_download_task_is_added_when_plugin_is_applied() throws IOException {

        String buildFileContent = "plugins { id 'io.github.atomfrede.gradle.crowdin-cli' }";

        writeFile(buildFile, buildFileContent);

        BuildResult result = GradleRunner.create()
            .withPluginClasspath()
            .withProjectDir(testProjectDir.getRoot())
            .withArguments("tasks")
            .build();

        assertThat(result.getOutput())
            .as("Crowdin task group is created")
            .contains("Crowdin tasks")
            .as("Download crowdin cli task is created")
            .contains("downloadCrowdinCli");

    }

    @Test
    public void test_crowdin_cli_unzip_task_is_added_when_plugin_is_applied() throws IOException {

        String buildFileContent = "plugins { id 'io.github.atomfrede.gradle.crowdin-cli' }";

        writeFile(buildFile, buildFileContent);

        BuildResult result = GradleRunner.create()
            .withPluginClasspath()
            .withProjectDir(testProjectDir.getRoot())
            .withArguments("tasks")
            .build();

        assertThat(result.getOutput())
            .as("Crowdin task group is created")
            .contains("Crowdin tasks")
            .as("Unzip crowdin cli task is created")
            .contains("unzipCrowdinCli");

    }

    @Test
    public void test_download_of_crowdin_cli_zip() throws IOException {

        String buildFileContent = "plugins { id 'io.github.atomfrede.gradle.crowdin-cli' }";

        writeFile(buildFile, buildFileContent);

        GradleRunner.create()
            .withPluginClasspath()
            .withProjectDir(testProjectDir.getRoot())
            .withArguments("downloadCrowdinCli")
            .build();

        Path path = Paths.get(testProjectDir.getRoot().getAbsolutePath(), "gradle", "crowdin-cli", "crowdin-cli.zip");

        assertThat(Files.exists(path)).as("crowdin-cli.zip has been downloaded successfully.").isTrue();
    }

    private void writeFile(File destination, String content) throws IOException {
        BufferedWriter output = null;
        try {

            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destination), Charset.forName("UTF-8")));
            output.write(content);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}
