package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git.GitConfig;

public class CrowdinSync extends CrowdinCli implements CrowdinSyncSpec {

    protected String branch;
    protected boolean dryRun;
    protected boolean tree;
    protected GitConfig git;

    @Override
    public void branch(String branch) {

        this.branch = branch;
    }

    @Override
    public void dryRun(boolean dryRun) {

        this.dryRun = dryRun;
    }

    @Override
    public void tree(boolean showTree) {

        this.tree = showTree;
    }
}
