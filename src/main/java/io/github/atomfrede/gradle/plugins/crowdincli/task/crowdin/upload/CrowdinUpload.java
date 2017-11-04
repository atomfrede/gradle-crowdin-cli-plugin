package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.upload;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinSync;
import org.gradle.api.tasks.TaskAction;

import java.util.ArrayList;

public class CrowdinUpload extends CrowdinSync implements CrowdinUploadSpec {

    private boolean autoUpdate = true;

    public CrowdinUpload() {

        this.command = new ArrayList<>();
        this.command.add("upload");
        this.command.add("sources");

    }

    @Override
    public void autoUpdate(boolean enabled) {

        this.autoUpdate = enabled;
    }

    @TaskAction
    public void exec() {

        if (isDryRun()) {
            this.command.add("--dryrun");
        }

        super.exec();


    }
}
