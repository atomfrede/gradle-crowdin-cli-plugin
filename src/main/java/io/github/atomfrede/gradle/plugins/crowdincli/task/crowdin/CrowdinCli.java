package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

import io.github.atomfrede.gradle.plugins.crowdincli.CrowdinCliPlugin;
import io.github.atomfrede.gradle.plugins.crowdincli.task.download.CrowdinCliUnzipTask;
import org.gradle.api.Task;
import org.gradle.api.tasks.AbstractExecTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CrowdinCli extends AbstractExecTask<CrowdinCli> implements CrowdinSpec {

    private static final List<String> CROWDIN_CLI_COMMAND = Arrays.asList("java", "-jar", "./gradle/crowdin-cli/crowdin-cli.jar");

    private List<String> command;
    private File configFile;
    private File identityFile;

    public CrowdinCli() {

        super(CrowdinCli.class);

        setGroup(CrowdinCliPlugin.GROUP);

        setDependsOn(Collections.singleton(getUnzipTask()));

        command = new ArrayList<>();

    }

    @Override
    public void configFile(File configFile) {

        this.configFile = configFile;
    }

    @Override
    public void identityFile(File identity) {

        this.identityFile = identity;
    }

    @Override
    public void command(String... arguments) {

        command.addAll(Arrays.asList(arguments));

    }

    @Override
    public void verbose(boolean verbose) {

    }

    @Override
    public File getConfigFile() {

        return configFile;
    }

    @Override
    public File getIdentity() {

        return identityFile;
    }

    @Override
    public List<String> getCommand() {
        return command;
    }

    private CrowdinCliUnzipTask getUnzipTask() {

        Set<Task> tasks = getProject().getTasksByName(CrowdinCliUnzipTask.TASK_NAME, true);

        List<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks);

        return (CrowdinCliUnzipTask) allTasks.get(0);
    }

    @Override
    @TaskAction
    protected void exec() {

        List<String> commandLine = new ArrayList<>();
        commandLine.addAll(CROWDIN_CLI_COMMAND);
        commandLine.addAll(getCommand());

        setCommandLine(commandLine);

        super.exec();
    }

}
