package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.upload;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinSpec;

public interface CrowdinUploadSpec extends CrowdinSpec {

    void dryRun(boolean dryRun);

    boolean isDryRun();
}
