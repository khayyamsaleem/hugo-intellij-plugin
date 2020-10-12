# hugo-intellij-plugin

Hugo static site generator plugin for Intellij

## Features
* Hugo Run Configuration to start Hugo Server or execute any other Hugo command with optional arguments
* Added action to New group of Project View context menu to execute `hugo new`
* Allow to manually set the path to Hugo executable in `Settings - Tools - Hugo Plugin`
* Option to set default arguments to be passed to `hugo new`

*Note: If the Hugo executable is not in your* `PATH` *variable, you have to set the path in the settings*

## Planned features
* Hugo Facet to automatically add run configuration when starting a new project
* Toggles for the most used arguments, so they don't have to be manually typed in the arguments field

## Contributing
1. Fork the repository
2. Install all required dependencies for your platform (`gradle`, `jdk1.8+`, IntelliJ CE/Ultimate)
3. If you encounter any errors on `@Getter` or `@Setter` annotations in IntelliJ, install the `lombok` plugin. You may also be asked to enable annotation processing.
4. Run `gradle build` as you iterate on this codebase to validate that the plugin builds.
5. Test out your generated plugin using the zip files built in `./build/distributions/`, using IntelliJ's `Install plugin from disk` capability.
6. When you are ready to merge back in:
    - Update the version in the `build.gradle` file in accordance with [SemVer](http://semver.org)
    - Create a pull request from your fork to `1v0dev/hugo-intellij-plugin:master`, tagging any issues that your change closes in your commit messages. (_e.g. `git commit -m "Closes #53"`_)
    - Once your pull request is reviewed and approved and merged, follow up with `@1v0dev` to ensure that the new version is uploaded to the IntelliJ marketplace.
7. 
