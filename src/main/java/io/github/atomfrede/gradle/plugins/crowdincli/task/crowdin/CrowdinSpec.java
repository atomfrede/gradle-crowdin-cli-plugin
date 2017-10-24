package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

import java.io.File;

public interface CrowdinSpec {

    void configFile(File configFile);

    void identityFile(File identity);


}
