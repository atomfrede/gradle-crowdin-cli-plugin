package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git;

public interface GitSpec {

    void enable(boolean enable);

    void commitMessage(String commitMessage);

    String getCommitMessage();

    boolean isEnabled();
}
