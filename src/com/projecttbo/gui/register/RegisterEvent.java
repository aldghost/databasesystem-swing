package com.projecttbo.gui.register;

import java.util.EventObject;

public class RegisterEvent extends EventObject {
    private String namaLengkap, username, email, password;

    public RegisterEvent(Object source) {
        super(source);
    }

    public RegisterEvent(Object source, String namaLengkap, String username, String email, String password) {
        super(source);
        this.namaLengkap = namaLengkap;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
