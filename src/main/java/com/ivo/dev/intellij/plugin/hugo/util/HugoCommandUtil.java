package com.ivo.dev.intellij.plugin.hugo.util;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.project.Project;
import com.ivo.dev.intellij.plugin.hugo.config.HugoSettings;

import org.apache.commons.lang.StringUtils;

public class HugoCommandUtil {
    private static final String DEFAULT_EXE_PATH = "hugo";
    private static final String SERVER_COMMAND = "server";

    public static GeneralCommandLine createHugoCommand(
            final boolean runServer,
            final String arguments,
            final Project project,
            final String workingDir
    ) {
        HugoSettings hugoSettings = HugoSettings.getInstance(project);

        GeneralCommandLine commandLine = new GeneralCommandLine();
        if (hugoSettings.isUseCustomPath()) {
            commandLine.setExePath(hugoSettings.getCustomHugoPath());
        } else {
            commandLine.setExePath(DEFAULT_EXE_PATH);
        }

        if (StringUtils.isNotEmpty(workingDir)) {
            commandLine.setWorkDirectory(workingDir);
        } else {
            commandLine.setWorkDirectory(project.getBasePath());
        }

        if (runServer) {
            commandLine.addParameter(SERVER_COMMAND);
        }

        if (StringUtils.isNotEmpty(arguments)) {
            commandLine.addParameters(arguments.trim().split("\\s+"));
        }

        return commandLine;
    }

}
