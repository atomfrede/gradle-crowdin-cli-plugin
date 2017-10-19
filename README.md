# gradle-crowdin-cli-plugin
A gradle plugin to utilize the crowdin cli to interact with crowdin

## Usage

You need to copy the crowin-cli into you project and configure where to find the executable cli jar file (relative to your build file):

```
crowdin {
  cli = 'crowdin-cli.jar'
}
```

The plugin adds two task which use the default crowdin config file `crowdin.yaml`. You can upload source files via `crowdinUpload` and download translated files via `crowdinDownload`.

## Custom Tasks

You can create own task to customize both upload and download depending on you configuration:

```
task uploadCustom (type: CrowdinUpload) {
  config = 'myCustomConfig.yaml'
}

task downloadCustom (type: CrowdinDownload) {
  config = 'myCustomConfig.yaml'
}
```

## Git Integration

You can use this plugin to automate and integration crowdin into your build pipeline. E.g. let jenkins upload new sources and download translations which are then also commited to git. The plugin checks for the configured files in the config such it can't happen you ci will commit other files except translation files. The used commit message can be customized.

You can configure git integration on a global level for all crowdin task. By default git integration is disabled.

```
crowdin {
  git {
   enable = true,
   commitMessage = 'Sync Translations via crowdin`
  }
}
```

Or for each task:

```
task downloadCustom (type: CrowdinDownload) {
  config = 'myCustomConfig.yaml'
  git {
   enable = true,
   commitMessage = 'My Custom Commit Message'
  }
}
```
