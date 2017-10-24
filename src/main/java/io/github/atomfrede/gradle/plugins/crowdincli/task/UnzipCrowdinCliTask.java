package io.github.atomfrede.gradle.plugins.crowdincli.task;

import io.github.atomfrede.gradle.plugins.crowdincli.CrowdinCliPlugin;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.Task;
import org.gradle.api.internal.file.FileResolver;
import org.gradle.api.internal.file.copy.CopyAction;
import org.gradle.api.internal.file.copy.CopySpecInternal;
import org.gradle.api.internal.file.copy.DestinationRootCopySpec;
import org.gradle.api.internal.file.copy.FileCopyAction;
import org.gradle.api.tasks.AbstractCopyTask;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.internal.reflect.Instantiator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UnzipCrowdinCliTask extends AbstractCopyTask {

    public static final String NAME = "unzipCrowdinCli";
    public static final String DESCRIPTION = "Unzip the crowdin cli into gradle/crowdin-cli";

    public UnzipCrowdinCliTask() {

        super();
        getProject().getTasksByName("unzipCrowdinCli", true);
        setDependsOn(Collections.singletonList(getDownloadTask()));
        setGroup(CrowdinCliPlugin.GROUP);
        setDescription(DESCRIPTION);

        from(getProject().zipTree(getDownloadTask().getDest()));

        File destination = new File(getDownloadTask().getDest().getParentFile(), "crowdin-cli");
        setDestinationDir(destination);


    }

    private CrowdinCliDownloadTask getDownloadTask() {

        Set<Task> tasks = getProject().getTasksByName(CrowdinCliDownloadTask.TASK_NAME, true);

        List<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks);

        return (CrowdinCliDownloadTask) allTasks.get(0);
    }

    protected CopyAction createCopyAction() {
        File destinationDir = this.getDestinationDir();
        if (destinationDir == null) {
            throw new InvalidUserDataException("No copy destination directory has been specified, use 'into' to specify a target directory.");
        } else {
            return new FileCopyAction(this.getFileLookup().getFileResolver(destinationDir));
        }
    }

    protected CopySpecInternal createRootSpec() {
        Instantiator instantiator = this.getInstantiator();
        FileResolver fileResolver = this.getFileResolver();
        return (CopySpecInternal)instantiator.newInstance(DestinationRootCopySpec.class, new Object[]{fileResolver, super.createRootSpec()});
    }

    public DestinationRootCopySpec getRootSpec() {
        return (DestinationRootCopySpec)super.getRootSpec();
    }

    @OutputDirectory
    public File getDestinationDir() {
        return this.getRootSpec().getDestinationDir();
    }

    public void setDestinationDir(File destinationDir) {
        this.into(destinationDir);
    }
}
