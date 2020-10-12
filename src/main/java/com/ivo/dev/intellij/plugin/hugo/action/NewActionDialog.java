package com.ivo.dev.intellij.plugin.hugo.action;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;

import com.ivo.dev.intellij.plugin.hugo.config.HugoSettings;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewActionDialog extends DialogWrapper {
    private static final String NEW_ACTION_TITLE = "Hugo New";
    private JPanel dialogPanel;
    private JTextField fileNameField;
    private JTextField argumentsField;
    private JCheckBox bundleCheckbox;
    private NewActionDTO dto;

    protected NewActionDialog(@Nullable Project project, NewActionDTO dto) {
        super(project);
        this.dto = dto;
        init();
        setTitle(NEW_ACTION_TITLE);
        setAutoAdjustable(true);

        HugoSettings hugoSettings = HugoSettings.getInstance(project);
        if (StringUtils.isNotEmpty(hugoSettings.getDefaultHugoNewOptions())) {
            argumentsField.setText(hugoSettings.getDefaultHugoNewOptions());
        }
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return dialogPanel;
    }

    @Override
    protected void doOKAction() {
        dto.setFileName(fileNameField.getText());
        dto.setArguments(argumentsField.getText());
        dto.setBundle(bundleCheckbox.isSelected());
        super.doOKAction();
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        if (StringUtils.isEmpty(fileNameField.getText())) {
            return new ValidationInfo("File name cannot be empty", fileNameField);
        }
        return null;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return fileNameField;
    }
}
