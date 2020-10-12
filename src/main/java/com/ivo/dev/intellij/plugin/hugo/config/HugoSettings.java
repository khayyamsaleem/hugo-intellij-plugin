package com.ivo.dev.intellij.plugin.hugo.config;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
    name = "HugoPluginSettings",
    storages = {
        @Storage(StoragePathMacros.WORKSPACE_FILE)
    }
)
@Getter
@Setter
public class HugoSettings implements PersistentStateComponent<HugoSettings> {

    private boolean useCustomPath = false;

    private String customHugoPath;

    private String defaultHugoNewOptions;

    @Nullable
    @Override
    public HugoSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull HugoSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public static HugoSettings getInstance(Project project) {
        return ServiceManager.getService(project, HugoSettings.class);
    }
}
