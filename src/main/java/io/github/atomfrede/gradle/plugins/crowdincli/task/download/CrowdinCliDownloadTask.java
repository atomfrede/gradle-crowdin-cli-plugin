package io.github.atomfrede.gradle.plugins.crowdincli.task.download;

import de.undercouch.gradle.tasks.download.Download;

import java.net.MalformedURLException;

public class CrowdinCliDownloadTask extends Download {

    public static final String TASK_NAME = "downloadCrowdinCli";
    public static final String DESCRIPTION = "Downloads the latest crowdin cli archive to into gradle/crowdin-cli/crowdin-cli.zip";

    private static final String crowdinCliDownloadUrl = "https://downloads.crowdin.com/cli/v2/crowdin-cli.zip";

    public CrowdinCliDownloadTask() {

        super();

        try {
            src(crowdinCliDownloadUrl);
            dest("./gradle/crowdin-cli/crowdin-cli.zip");
            onlyIfModified(true);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
