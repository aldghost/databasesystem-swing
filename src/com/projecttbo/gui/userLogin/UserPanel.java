package com.projecttbo.gui.userLogin;

import com.projecttbo.gui.login.LoginEvent;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    private JButton logoutBtn;
    private JLabel jLabel;
    private UserListener userListener;

    public UserPanel(){

        logoutBtn = new JButton("Logout");
        jLabel = new JLabel("User Panel");


        setLayout();


        logoutBtn.addActionListener((event) -> {
            if(this.userListener != null)
                this.userListener.hasilUserListener();
        });
    }

    private void setLayout(){
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        addGBC(gbc, 0, 4, GridBagConstraints.CENTER);
        add(logoutBtn, gbc);


        addGBC(gbc, 0, 1, GridBagConstraints.CENTER);
        add(jLabel, gbc);

    }
    public void addGBC(GridBagConstraints gbc, int gridX, int gridY, int anchor){
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.anchor = anchor;
    }

    public void setHasilListener(UserListener userListener){
        this.userListener = userListener;
    }



}
