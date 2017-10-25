package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

public interface CrowdinSyncSpec extends CrowdinSpec {

    void branch(String branch);
    void dryRun(boolean dryRun);
    void tree(boolean showTree);

}
