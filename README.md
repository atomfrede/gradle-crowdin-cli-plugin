[![Build Status](https://img.shields.io/travis/atomfrede/gradle-crowdin-cli-plugin.svg?style=flat-square)](https://travis-ci.org/atomfrede/gradle-crowdin-cli-plugin)
[![Codacy](https://img.shields.io/codacy/43d1920955b74cacbf38b14e45905f14.svg?style=flat-square)](https://www.codacy.com/app/frederik-hahne/gradle-crowdin-cli-plugin/dashboard)
[![Codecov branch](https://img.shields.io/codecov/c/github/atomfrede/gradle-crowdin-cli-plugin/master.svg?style=flat-square)](https://codecov.io/github/atomfrede/gradle-crowdin-cli-plugin?branch=master)

# gradle-crowdin-cli-plugin
A gradle plugin to utilize the crowdin cli to interact with crowdin

## Usage

The plugin will download the `crowin-cli.jar` for you into `gradle/crowdin-cli/crowdin-cli/crowdin-cli.jar`. 
The plugin always downloads the current latest cli. The download task checks if the zip file on server is newer than the downloaded one and download the newer file automatically.

The plugin adds two task which use the default crowdin config file `crowdin.yaml`. You can upload source files via `crowdinUpload` and download translated files via `crowdinDownload`.

## Simple Tasks

```gradle
task crowdinHelp (type: CrowdinCli) {
    command 'help'
}

task crowdinLint (type: CrowdinCli) {
    command 'lint'
}
```

### Authentication

As your crowdin config file should not contain the api key you need to provide it via command line parameters.
The cli can handle specific environment variables to read the api key from.

As all tasks extend the exec task providing a the right environment variables is easy:

```gradle
task uploadSources (type: CrowdinUpload) {
    if (project.hasProperty('crowdinApiKey')) {
        environment = ['CROWDIN_API_KEY': crowdinApiKey]
    }
}
```
You should check for the existence of the required property, otherwise you won't be able to execute e.g. `gradle tasks` without providing the appropriate property.
 
With following in the zour config file  `"api_key_env": CROWDIN_API_KEY` you can just trigger the upload with `gradle uploadSoures -PcrowdinApiKey=YOUR_API_KEY`.

## Custom Tasks

You can create own task to customize both upload and download depending on you configuration:

```gradle
task uploadCustom (type: CrowdinUpload) {
  config = 'myCustomConfig.yaml'
}

task downloadCustom (type: CrowdinDownload) {
  config = 'myCustomConfig.yaml'
}
```

## Git Integration

You can use this plugin to automate and integration crowdin into your build pipeline. E.g. let jenkins upload new sources and download translations which are then also commited to git. The plugin checks for the configured files in the config such it can't happen you ci will commit other files except translation files. The used commit message can be customized.

You can configure git integration for each task. By default git integration is disabled.

```gradle
task downloadCustom (type: CrowdinDownload) {
  git {
   enable = true,
   commitMessage = 'My Custom Commit Message'
  }
}
```
