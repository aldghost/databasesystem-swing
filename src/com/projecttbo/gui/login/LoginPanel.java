package com.projecttbo.gui.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LoginPanel extends JPanel{

    private JLabel usernameLabel, passwordLabel, comboLabel, verifLabel, askLabel;
    private JTextField usernameField, passwordField, askField;
    private JButton loginBtn;
    private LoginListener loginListener;
    private JComboBox<String> comboBox;
    private JCheckBox verifBox;


    public LoginPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 300;
        setPreferredSize(dim);

        verifBox = new JCheckBox();
        verifLabel = new JLabel("Are you human?: ");

        askLabel = new JLabel("15 + 23 ?: ");
        askField = new JTextField(10);

        askField.setEnabled(false);
        askLabel.setEnabled(false);

        usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField(10);

        passwordLabel = new JLabel("Password: ");
        passwordField = new JTextField(10);

        loginBtn = new JButton("Login");


        comboLabel = new JLabel("Role: ");
        comboBox = new JComboBox<>();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addElement("Student");
        comboBoxModel.addElement("Dosen");
        comboBoxModel.addElement("Admin");
        comboBox.setModel(comboBoxModel);

        loginBtn.setMnemonic(KeyEvent.VK_ENTER);
        loginBtn.addActionListener((event) -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = (String) comboBox.getSelectedItem();
            String verif = askField.getText();
            boolean verifSelected = verifBox.isSelected();

            LoginEvent fe = new LoginEvent(this, username, password, role, verif, verifSelected);
            if(this.loginListener != null){
                this.loginListener.loginEventHasil(fe);
                usernameField.setText("");
                passwordField.setText("");
                verifBox.setSelected(false);
                askField.setText("");
            }
        });

        verifBox.addActionListener((event) ->{
            boolean selected = verifBox.isSelected();

            askLabel.setEnabled(selected);
            askField.setEnabled(selected);

        });

        layoutSetting();



    }

    public void layoutSetting(){
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.NONE;
        addGBC(gbc, 0, 0, GridBagConstraints.LINE_END);
        add(usernameLabel, gbc);
        addGBC(gbc, 1, 0, GridBagConstraints.LINE_START);
        add(usernameField, gbc);

        gbc.weightx = 1;
        gbc.weighty = 0.1;
        addGBC(gbc, 0, 1, GridBagConstraints.LINE_END);
        add(passwordLabel, gbc);
        addGBC(gbc, 1, 1, GridBagConstraints.LINE_START);
        add(passwordField, gbc);

        gbc.weightx = 1;
        gbc.weighty = 0.2;
        addGBC(gbc, 0, 2, GridBagConstraints.LINE_END);
        add(comboLabel, gbc);
        addGBC(gbc, 1, 2, GridBagConstraints.LINE_START);
        add(comboBox, gbc);

        gbc.weightx = 1;
        gbc.weighty = 0.2;
        addGBC(gbc, 0, 3, GridBagConstraints.LINE_END);
        add(verifLabel, gbc);
        addGBC(gbc, 1, 3, GridBagConstraints.LINE_START);
        add(verifBox, gbc);

        gbc.weightx = 1;
        gbc.weighty = 1;
        addGBC(gbc, 0, 4, GridBagConstraints.LINE_END);
        add(askLabel, gbc);
        addGBC(gbc, 1, 4, GridBagConstraints.LINE_START);
        add(askField, gbc);


        gbc.weightx = 1;
        gbc.weighty = 2.0;

        addGBC(gbc, 1, 5, GridBagConstraints.LINE_START);
        add(loginBtn, gbc);
    }

    public void setLoginListener(LoginListener loginListener){
        this.loginListener = loginListener;
    }

    public void addGBC(GridBagConstraints gbc, int gridX, int gridY, int anchor){
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.anchor = anchor;
    }
}
