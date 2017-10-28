package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.download;

import groovy.lang.Closure;
import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.CrowdinSyncSpec;
import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git.GitConfig;
import org.gradle.api.Action;

public interface CrowdinDownloadSpec extends CrowdinSyncSpec {

    void language(String language);

    void git(Closure<GitConfig> git);

    void git(Action<? super GitConfig> action);

}
