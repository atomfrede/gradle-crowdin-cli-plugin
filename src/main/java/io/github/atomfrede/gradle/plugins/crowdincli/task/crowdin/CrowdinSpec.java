package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

import java.io.File;
import java.util.List;

/**
 * A simple config spec for tasks interacting with crowdin cli and a config file.
 *
 * If now config file is provided the default crowdin.yaml will be used.
 */
public interface CrowdinSpec {

    void configFile(File configFile);

    void identityFile(File identity);

    void command(List<String> command);


}
