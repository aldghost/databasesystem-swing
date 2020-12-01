package com.projecttbo.gui.changePassword;

import java.util.EventObject;

public class ChangePasswordEvent extends EventObject {
    private String username;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordEvent(Object source) {
        super(source);
    }

    public ChangePasswordEvent(Object source, String username, String oldPassword, String newPassword) {
        super(source);
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
