package com.projecttbo.gui.login;

import java.util.EventObject;

public class LoginEvent extends EventObject {
    private String username;
    private String password;

    private String role;
    private String verif;
    private boolean verifSelected;


    public LoginEvent(Object source) {
        super(source);
    }
    public LoginEvent(Object source, String username, String password, String role, String verif, boolean verifSelected) {
        super(source);
        this.username = username;
        this.password = password;
        this.role = role;
        this.verif = verif;
        this.verifSelected = verifSelected;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVerif() {
        return verif;
    }

    public void setVerif(String verif) {
        this.verif = verif;
    }

    public boolean isVerifSelected() {
        return verifSelected;
    }

    public void setVerifSelected(boolean verifSelected) {
        this.verifSelected = verifSelected;
    }
}
