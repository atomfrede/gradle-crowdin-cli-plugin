package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git.GitSpec;

public interface CrowdinSyncSpec extends CrowdinSpec {

    void branch(String branch);

    void dryRun(boolean dryRun);

    void tree(boolean showTree);

}
