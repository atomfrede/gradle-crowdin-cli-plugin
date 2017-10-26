package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.upload;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinSpec;
import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinSyncSpec;

public interface CrowdinUploadSpec extends CrowdinSyncSpec {

    void autoUpdate(boolean enabled);

}
