package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

public interface CrowdinUploadSpec extends CrowdinSpec {

    void dryRun(boolean dryRun);

    boolean isDryRun();
}
