package io.github.atomfrede.gradle.plugins.crowdincli.task;

import de.undercouch.gradle.tasks.download.Download;

import java.net.MalformedURLException;

public class CrowdinCliDownloadTask extends Download {

    public static final String TASK_NAME = "downloadCrowdinCli";
    public static final String DESCRIPTION = "Downloads the latest crowdin cli to into gradle/X.X.X/crowdin-cli.jar";

    private static final String crowdinCliDownloadUrl = "https://downloads.crowdin.com/cli/v2/crowdin-cli.zip";

    public CrowdinCliDownloadTask() {

        super();

        try {
            src(crowdinCliDownloadUrl);
            dest("./gradle/crowdin-cli.zip");
            onlyIfModified(true);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
