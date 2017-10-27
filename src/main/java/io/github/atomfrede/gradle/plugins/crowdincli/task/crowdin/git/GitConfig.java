package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git;


public class GitConfig implements GitConfigSpec {

    private boolean isEnabled = false;
    private String commitMessage = "";
    private String authorEmail = "";
    private String authorName = "";

    @Override
    public void enable(boolean enable) {
        this.isEnabled = enable;
    }

    @Override
    public void commitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

    @Override
    public void authorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public void authorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    @Override
    public String getCommitMessage() {
        return this.commitMessage;
    }

    @Override
    public String getAuthorName() {
        return this.authorName;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String getAuthorEmail() {
        return this.authorEmail;
    }
}
