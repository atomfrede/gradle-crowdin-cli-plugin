package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.upload;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinSync;
import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.download.CrowdinDownloadSpec;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;

public class CrowdinUpload extends CrowdinSync implements CrowdinUploadSpec {

    private boolean autoUpdate = true;

    @Override
    public void autoUpdate(boolean enabled) {

    }
}
