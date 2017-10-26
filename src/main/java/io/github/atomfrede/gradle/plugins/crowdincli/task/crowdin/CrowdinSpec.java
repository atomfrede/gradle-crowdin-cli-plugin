package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

import groovy.lang.Closure;
import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git.Git;
import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git.GitSpec;

import java.io.File;
import java.util.List;

/**
 * A simple config spec for tasks interacting with crowdin cli and a config file.
 *
 * If now config file is provided the default crowdin.yaml will be used.
 */
public interface CrowdinSpec {

    /**
     * The config file to use for cli interaction. If you don't provide a config file the default is assumed, which is
     * crowdin.yaml
     * @param configFile
     */
    void configFile(File configFile);

    void identityFile(File identity);

    void command(String... arguments);

    void verbose(boolean verbose);

    File getConfigFile();

    File getIdentity();

    List<String> getCommand();

    boolean isVerbose();
}
