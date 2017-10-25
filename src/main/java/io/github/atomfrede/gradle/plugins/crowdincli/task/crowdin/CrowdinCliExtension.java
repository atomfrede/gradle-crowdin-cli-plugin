package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

import groovy.lang.Closure;
import org.gradle.api.Project;
import org.gradle.util.Configurable;

public class CrowdinCliExtension implements Configurable<CrowdinCliExtension> {


    public CrowdinCliExtension(Project project) {
    }

    @Override
    public CrowdinCliExtension configure(@SuppressWarnings("rawtypes") Closure closure) {

        return this;
    }
}
