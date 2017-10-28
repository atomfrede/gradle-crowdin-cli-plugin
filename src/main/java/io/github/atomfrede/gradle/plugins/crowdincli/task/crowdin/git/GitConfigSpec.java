package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git;

public interface GitConfigSpec {

    void enable(boolean enable);

    void commitMessage(String commitMessage);

    void authorName(String authorName);

    void authorEmail(String authorEmail);

    boolean isEnabled();

    String getCommitMessage();

    String getAuthorName();

    String getAuthorEmail();
}
