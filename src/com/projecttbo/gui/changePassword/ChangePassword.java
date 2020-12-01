package com.projecttbo.gui.changePassword;

import javax.swing.*;
import java.awt.*;

public class ChangePassword extends JPanel {
    private ChangePasswordListener chgListen;
    private JButton submitBtn;
    private JLabel usernameLabel, oldPasswordLabel, newPasswordLabel;
    private JTextField usernameField, oldPasswordField, newPasswordField;
    public ChangePassword(){
        submitBtn = new JButton("Change");
        usernameLabel = new JLabel("Username: ");
        oldPasswordLabel = new JLabel("Old Password: ");
        newPasswordLabel = new JLabel("New Password: ");

        usernameField = new JTextField(10);
        oldPasswordField = new JTextField(10);
        newPasswordField = new JTextField(10);


        aturLayout();

        submitBtn.addActionListener((event) -> {
            String username = usernameField.getText();
            String oldPassword = oldPasswordField.getText();
            String newPassword = newPasswordField.getText();

            ChangePasswordEvent cpe = new ChangePasswordEvent(this, username, oldPassword, newPassword);

            usernameField.setText("");
            oldPasswordField.setText("");
            newPasswordField.setText("");
            if(chgListen != null){
                this.chgListen.changeHasil(cpe);
            }
        });


    }

    private void aturLayout(){
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        addGBC(gbc, 0, 0, GridBagConstraints.LINE_END);
        add(usernameLabel, gbc);
        addGBC(gbc, 1, 0, GridBagConstraints.LINE_START);
        add(usernameField, gbc);

        gbc.weightx = 1;
        gbc.weighty = 0.1;

        addGBC(gbc, 0, 1, GridBagConstraints.LINE_END);
        add(oldPasswordLabel, gbc);
        addGBC(gbc, 1, 1, GridBagConstraints.LINE_START);
        add(oldPasswordField, gbc);

        gbc.weightx = 1;
        gbc.weighty = 0.1;

        addGBC(gbc, 0, 2, GridBagConstraints.LINE_END);
        add(newPasswordLabel, gbc);
        addGBC(gbc, 1, 2, GridBagConstraints.LINE_START);
        add(newPasswordField, gbc);


        gbc.weightx = 1;
        gbc.weighty = 2;

        addGBC(gbc, 1, 3, GridBagConstraints.LINE_START);
        add(submitBtn, gbc);
    }


    private void addGBC(GridBagConstraints gbc, int gridX, int gridY, int anchor){
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.anchor = anchor;
    }



    public void setCPListener(ChangePasswordListener chgListen){
        this.chgListen = chgListen;
    }

}
