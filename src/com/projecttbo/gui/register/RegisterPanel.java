package com.projecttbo.gui.register;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RegisterPanel extends JPanel {
    private JLabel usernameLabel, namaLengkapLabel, emailLabel, passwordLabel;
    private JTextField usernameField, namaLengkapField, emailField, passwordField;
    private JButton registerBtn, generateBtn;
    private RegisterListener registerListener;

    public RegisterPanel(){

        setLayout(new GridBagLayout());

        registerBtn = new JButton("Register");

        this.namaLengkapLabel = new JLabel("Nama Lengkap: ");
        this.namaLengkapField = new JTextField(10);

        this.usernameLabel = new JLabel("Username: ");
        this.usernameField = new JTextField(10);

        this.emailLabel = new JLabel("Email: ");
        this.emailField = new JTextField(10);

        this.passwordLabel = new JLabel("Password: ");
        this.passwordField = new JTextField(10);

        this.generateBtn = new JButton("Generate");




        generateBtn.addActionListener((event) -> {
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            passwordField.setText(generatedString);
        });


        registerBtn.addActionListener((event) -> {
            String namaLengkap = namaLengkapField.getText();
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            RegisterEvent re = new RegisterEvent(this, namaLengkap, username, email, password);

            if(this.registerListener != null){
                this.registerListener.registerEventHasil(re);

            }
        });


        setLayout();

    }

    private void setLayout(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.NONE;
        addGBC(gbc, 0, 0, GridBagConstraints.LINE_END);
        add(namaLengkapLabel, gbc);
        addGBC(gbc, 1, 0, GridBagConstraints.LINE_START);
        add(namaLengkapField, gbc);


        gbc.weightx = 1;
        gbc.weighty = 0.1;
        addGBC(gbc, 0, 1, GridBagConstraints.LINE_END);
        add(usernameLabel, gbc);
        addGBC(gbc, 1, 1, GridBagConstraints.LINE_START);
        add(usernameField, gbc);


        gbc.weightx = 1;
        gbc.weighty = 0.1;
        addGBC(gbc, 0, 2, GridBagConstraints.LINE_END);
        add(emailLabel, gbc);
        addGBC(gbc, 1, 2, GridBagConstraints.LINE_START);
        add(emailField, gbc);


        gbc.weightx = 1;
        gbc.weighty = 0.1;
        addGBC(gbc, 0, 3, GridBagConstraints.LINE_END);
        add(passwordLabel, gbc);
        addGBC(gbc, 1, 3, GridBagConstraints.LINE_START);
        add(passwordField, gbc);


        gbc.weightx = 1;
        gbc.weighty = 1.5;
        gbc.insets = new Insets(0, 2,0,2);
        addGBC(gbc, 0, 4, GridBagConstraints.LINE_END);
        add(generateBtn, gbc);
        addGBC(gbc, 1, 4, GridBagConstraints.LINE_START);
        add(registerBtn, gbc);

    }

    public void setRegisterListener(RegisterListener re){
        this.registerListener = re;
    }


    public void addGBC(GridBagConstraints gbc, int gridX, int gridY, int anchor){
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.anchor = anchor;
    }
}
