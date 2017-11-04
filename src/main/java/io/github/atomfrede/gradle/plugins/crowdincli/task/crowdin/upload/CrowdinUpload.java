package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.upload;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinSync;
import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.download.CrowdinDownloadSpec;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
