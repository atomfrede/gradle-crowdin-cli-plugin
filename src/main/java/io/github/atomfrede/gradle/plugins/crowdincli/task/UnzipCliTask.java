package io.github.atomfrede.gradle.plugins.crowdincli.task;

import org.gradle.api.tasks.Copy;

import java.io.File;
import java.util.Collections;

public class UnzipCliTask extends Copy {

    public UnzipCliTask(CrowdinCliDownloadTask crowdinCliDownloadTask) {

        super();

        this.setDependsOn(Collections.singletonList(crowdinCliDownloadTask));
        this.from(crowdinCliDownloadTask.getDest());
        this.setDestinationDir(new File("./gradle/"));

    }
}
