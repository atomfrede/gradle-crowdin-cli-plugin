package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

import io.github.atomfrede.gradle.plugins.crowdincli.CrowdinCliPlugin;
import io.github.atomfrede.gradle.plugins.crowdincli.task.download.CrowdinCliUnzipTask;
import org.gradle.api.Task;
import org.gradle.api.tasks.AbstractExecTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CrowdinCli extends AbstractExecTask<CrowdinCli> implements CrowdinSpec {

    protected static final List<String> CROWDIN_CLI_COMMAND = Arrays.asList("java", "-jar", "./gradle/crowdin-cli/crowdin-cli.jar");

    protected List<String> command;
    protected File configFile;
    protected boolean verbose = false;

    public CrowdinCli() {

        super(CrowdinCli.class);

        setGroup(CrowdinCliPlugin.GROUP);

        setDependsOn(Collections.singleton(findUnzipTask()));

        command = new ArrayList<>();

        configFile = new File(getProject().getProjectDir(), "crowdin.yml");
    }

    @Override
    public void configFile(File configFile) {

        this.configFile = configFile;
    }

    @Override
    public void command(String... arguments) {

        command.addAll(Arrays.asList(arguments));

    }

    @Override
    public void verbose(boolean verbose) {

        this.verbose = verbose;

    }

    @InputFile
    @Optional
    @Override
    public File getConfigFile() {
        return configFile;
    }

    @Override
    @Input
    public List<String> getCommand() {
        return command;
    }

    @Override
    @Input
    public boolean isVerbose() {

        return this.verbose;
    }

    private CrowdinCliUnzipTask findUnzipTask() {

        Set<Task> tasks = getProject().getTasksByName(CrowdinCliUnzipTask.TASK_NAME, true);

        List<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks);

        return (CrowdinCliUnzipTask) allTasks.get(0);
    }

    @TaskAction
    protected void exec() {

        List<String> commandLine = new ArrayList<>();
        commandLine.addAll(CROWDIN_CLI_COMMAND);

        if (isVerbose()) {
            commandLine.add("--verbose");
        }

        commandLine.addAll(getCommand());

        setCommandLine(commandLine);

        System.out.println(commandLine);
        super.exec();
    }

}
