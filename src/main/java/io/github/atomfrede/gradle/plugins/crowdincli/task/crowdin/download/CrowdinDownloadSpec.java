package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.download;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinSyncSpec;

public interface CrowdinDownloadSpec extends CrowdinSyncSpec {

    void language(String language);

}
