package io.github.atomfrede.gradle.plugins.crowdincli.task.download;

import io.github.atomfrede.gradle.plugins.crowdincli.CrowdinCliPlugin;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.Task;
import org.gradle.api.file.RelativePath;
import org.gradle.api.internal.file.FileResolver;
import org.gradle.api.internal.file.copy.CopyAction;
import org.gradle.api.internal.file.copy.CopySpecInternal;
import org.gradle.api.internal.file.copy.DestinationRootCopySpec;
import org.gradle.api.internal.file.copy.FileCopyAction;
import org.gradle.api.tasks.AbstractCopyTask;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.internal.reflect.Instantiator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * This tasks depends on @{@link CrowdinCliDownloadTask} and extracts the downloaded
 * crowdin-cli.zip to 'gradle/corowdin-cli/crowdin-cli.jar`. The content of the
 * archive is flatted such that only the executable jar file is extracted to the destination directory.
 */
public class CrowdinCliUnzipTask extends Copy {

    public static final String TASK_NAME = "unzipCrowdinCli";
    public static final String DESCRIPTION = "Unzip the crowdin cli into gradle/crowdin-cli";

    public CrowdinCliUnzipTask() {

        super();
        getProject().getTasksByName("unzipCrowdinCli", true);
        setDependsOn(Collections.singletonList(getDownloadTask()));
        setGroup(CrowdinCliPlugin.GROUP);
        setDescription(DESCRIPTION);

        // only include the crowdin-cli.jar
        include("**/*.jar");
        setIncludeEmptyDirs(false);

        from(getProject().zipTree(getDownloadTask().getDest()));

        setDestinationDir(getDownloadTask().getDest().getParentFile());

        // flatten the result, such that we have a stable path where to find the crowdin cli executable
        eachFile(fileCopyDetails -> fileCopyDetails.setRelativePath(new RelativePath(true, fileCopyDetails.getName())));

    }

    private CrowdinCliDownloadTask getDownloadTask() {

        Set<Task> tasks = getProject().getTasksByName(CrowdinCliDownloadTask.TASK_NAME, true);

        List<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasks);

        if (allTasks.size() == 0) {
            getLogger().error("Required task {} is missing.", CrowdinCliDownloadTask.TASK_NAME);
            throw new RuntimeException(String.format("Required task %s is missing.", CrowdinCliDownloadTask.TASK_NAME));
        }

        return (CrowdinCliDownloadTask) allTasks.get(0);
    }
}
