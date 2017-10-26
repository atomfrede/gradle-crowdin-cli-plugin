package io.github.atomfrede.gradle.plugins.crowdincli.task.crowdin.git;

import java.io.File;

public class GitCommitTask {
//
//    /**
//     * Convenience function for staging a file-tree for git commit
//     */
//    def stageFiles = { tree ->
//        // Determine modified and added files.
//        def unstaged = grgit.status().unstaged
//        def modified = unstaged.modified
//        def added = unstaged.added
//
//        def projectDir = new File("$projectDir").toPath()
//        // grgit.add expects a list of strings representing relative pathes
//        def list = tree.getFiles().collect { return projectDir.relativize(it.toPath()).toString() }
//    // filter out files that did not change or were not added
//    def toStage = list.findAll { modified.contains(it) || added.contains(it) }
//
//    if (toStage.empty) {
//      logger.lifecycle('Nothing staged.')
//    return false
//    } else {
//      toStage.each { file ->
//      def text = 'Staging: ' + file
//      logger.lifecycle(text)
//    }
//      grgit.add(patterns: toStage)
//      return true
//    }
//    }
//
///**
// * Convenience function for commiting all translated files from a crowdin config file.
// */
//    def commitTranslations = { config, message ->
//    def yamlCfg = new File(config)
//    def crowdinConfig = new Yaml().load(yamlCfg.newInputStream())
//
//    def commitNotEmpty = false
//    def fileSpecs = crowdinConfig.get('files')
//    def basePath = crowdinConfig.get('base_path') ?: '.'
//
//    fileSpecs.each { spec ->
//    def transFile = spec.get('translation').replaceAll(/%.*%/, '*')
//    def files = fileTree(basePath).include(transFile)
//    commitNotEmpty = stageFiles(files) || commitNotEmpty
//    }
//
//    if (commitNotEmpty) {
//    logger.lifecycle('Comitting staged translation files.');
//    grgit.commit(message: message)
//    } else {
//    logger.lifecycle('Nothing to commit.')
//    }
//    }
}
