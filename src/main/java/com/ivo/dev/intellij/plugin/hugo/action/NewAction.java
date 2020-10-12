package com.ivo.dev.intellij.plugin.hugo.action;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.ivo.dev.intellij.plugin.hugo.util.HugoCommandUtil;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Context menu action handler for `hugo new` command
 */
public class NewAction extends AnAction {
    private static final String SPACE = " ";
    private static final String SLASH = "/";
    private static final String DASH = "-";
    private static final String NEW_COMMAND = "new";
    private static final String INDEX_FILE_NAME = "index.md";
    private static final String MD_EXT = ".md";

    private static final int HUGO_COMMAND_TIMEOUT = 15;

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        VirtualFile vf = anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE);
        NewActionDTO dto = new NewActionDTO();
        boolean ok = new NewActionDialog(anActionEvent.getProject(), dto).showAndGet();
        if (ok && vf != null) {
            runNewCommand(dto, vf, anActionEvent.getProject());
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getData(CommonDataKeys.NAVIGATABLE) instanceof PsiDirectory);
    }

    private String buildNewDocumentFilePath(NewActionDTO newActionDTO, VirtualFile vf) {
        StringBuilder sb = new StringBuilder();
        String cleanFileName = newActionDTO.getFileName()
                .replace(SPACE, DASH)
                .replaceAll("[)(\\[\\]]","");
        sb.append(vf.getPath()).append(SLASH).append(
                cleanFileName.endsWith(MD_EXT) ?
                        cleanFileName.substring(0, cleanFileName.length() - MD_EXT.length()) :
                        cleanFileName
        );
        if (newActionDTO.isBundle()) {
            sb.append(SLASH).append(INDEX_FILE_NAME);
        } else {
            sb.append(MD_EXT);
        }
        return sb.toString().toLowerCase();
    }

    /**
     * Runs `hugo new` command with all supplied parameters; creating leaf bundles if requested.
     *
     * @param dto object storing Hugo NewAction form responses
     * @param vf Intellij Virtual File
     * @param project Intellij Project instance
     */
    private void runNewCommand(NewActionDTO dto, VirtualFile vf, Project project) {
        StringBuilder sb = new StringBuilder(NEW_COMMAND + SPACE);
        sb.append(buildNewDocumentFilePath(dto, vf));
        if (StringUtils.isNotBlank(dto.getArguments())) {
            sb.append(SPACE).append(dto.getArguments());
        }
        GeneralCommandLine command = HugoCommandUtil.createHugoCommand(false, sb.toString(), project, null);
        try {
            FileDocumentManager.getInstance().saveAllDocuments();
            command.createProcess().waitFor(HUGO_COMMAND_TIMEOUT, TimeUnit.SECONDS);
            vf.refresh(false, true);
        } catch (Exception e) {
            Notification ntf = new Notification(
                    "Hugo Notifications",
                    "Hugo new error",
                    e.getMessage(),
                    NotificationType.ERROR
            );
            Notifications.Bus.notify(ntf);
        }
    }
}
