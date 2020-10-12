package com.ivo.dev.intellij.plugin.hugo.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.openapi.util.IconLoader;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;

public class HugoRunConfigurationType implements ConfigurationType {
    private static final String CONFIGURATION_TYPE_ID = "HUGO_RUN_CONFIGURATION";
    private static final String HUGO_ICON_PATH = "/images/hugo-icon-15.png";
    private static final String DISPLAY_NAME = "Hugo";

    @NotNull
    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "Run configuration for hugo server";
    }

    @Override
    public Icon getIcon() {
        return IconLoader.getIcon(HUGO_ICON_PATH);
    }

    @NotNull
    @Override
    public String getId() {
        return CONFIGURATION_TYPE_ID;
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[] {
                new HugoConfigurationFactory(this)
        };
    }

}
