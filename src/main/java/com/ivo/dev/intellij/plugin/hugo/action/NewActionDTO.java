package com.ivo.dev.intellij.plugin.hugo.action;

import lombok.Getter;
import lombok.Setter;

/**
 * Stores responses from NewActionDialog form
 */
@Getter
@Setter
public class NewActionDTO {

    private String fileName;
    private String arguments;
    private boolean bundle;

}
