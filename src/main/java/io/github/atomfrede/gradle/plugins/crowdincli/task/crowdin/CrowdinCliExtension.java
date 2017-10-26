package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin;

import io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git.Git;
import org.gradle.api.Action;
import org.gradle.api.Project;

public class CrowdinCliExtension{

    private final Project project;
    private final Git git = new Git();

    public CrowdinCliExtension(Project project) {

        this.project = project;
    }

//    public void setCrowdinCli(CrowdinCli crowdinCli) {
//        this.crowdinCli = crowdinCli;
//    }
//
//    public CrowdinCli getCrowdinCli() {
//        return crowdinCli;
//    }
//
    public Git getGit() {
        return git;
    }
//
    public void git(Action<? super Git> action) {
        action.execute(git);
    }


}
