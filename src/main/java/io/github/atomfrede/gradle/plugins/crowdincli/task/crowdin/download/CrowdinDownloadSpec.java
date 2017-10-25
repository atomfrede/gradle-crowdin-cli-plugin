package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.download;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinSpec;

public interface CrowdinDownloadSpec extends CrowdinSpec {

    void dryRun(boolean dryRun);

    boolean isDryRun();
}
