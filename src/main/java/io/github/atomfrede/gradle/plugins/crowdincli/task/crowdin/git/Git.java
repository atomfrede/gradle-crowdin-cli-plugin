package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git;

public class Git implements GitSpec {

    private boolean isEnabled = false;
    private String commitMessage = "";

    @Override
    public void enable(boolean enable) {

        this.isEnabled = enable;
    }

    @Override
    public void commitMessage(String commitMessage) {

        this.commitMessage = commitMessage;
    }

    @Override
    public String getCommitMessage() {
        return this.commitMessage;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
