package com.projecttbo.gui;

import com.projecttbo.gui.changePassword.ChangePassword;
import com.projecttbo.gui.login.LoginPanel;
import com.projecttbo.gui.register.RegisterPanel;
import com.projecttbo.gui.userLogin.UserListener;
import com.projecttbo.gui.userLogin.UserPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFrame extends JFrame {
    private TextPanel textPanel;
    private JButton button;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private ChangePassword changePassword;
    private JTabbedPane jTabbedPane1, jTabbedPane2;
    private UserPanel userPanel;
    private About about;


    public MainFrame(){
        init();
        addSomething();
        setUpTab();
    }

    private void init(){
        setTitle("Project TBO");
        setLayout(new BorderLayout());
        setSize(600,300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }



    private void addSomething(){



        textPanel = new TextPanel();
        button = new JButton("Submit");
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
        changePassword = new ChangePassword();
        userPanel = new UserPanel();
        about = new About();

        button.addActionListener((event) -> textPanel.append("Button di click\n"));

        loginPanel.setLoginListener((fe) -> {

            if(!fe.isVerifSelected() || !fe.getVerif().equals("38")){
                textPanel.append("Kode Verifikasi Salah\n");
                JOptionPane.showMessageDialog(this, "Isi Kode Keamanan dengan benar");
                return;
            }

            String name = fe.getUsername();
            String password = fe.getPassword();
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            try(Connection connection = DriverManager.getConnection("jdbc:sqlite:tbo.db");
                PreparedStatement preparedStatement = connection.prepareStatement(query)){

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                    if(resultSet.next()){
                        JOptionPane.showMessageDialog(this, "Login Sukses");
                        textPanel.append("Sukses Login - "+resultSet.getString("email")+" ("+fe.getRole()+")\n");

                        addAndRemove(jTabbedPane2, jTabbedPane1, BorderLayout.CENTER);
                    }else{
                        textPanel.append("Gagal Login\n");
                        JOptionPane.showMessageDialog(this, "Login Gagal");
                    }
                    resultSet.close();
            }catch (Exception e){
                System.out.println(e);
            }
        });



        registerPanel.setRegisterListener((event -> {
            Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");


            String namaLengkap = event.getNamaLengkap();
            String username = event.getUsername();
            String email = event.getEmail();
            String password = event.getPassword();



            if(namaLengkap.isEmpty() || username.isEmpty() ||
            email.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(this, "Mohon isi form dengan benar");
                textPanel.append("Isi form ada yang kosong\n");
                return;
            }

            Matcher matcher = pattern.matcher(namaLengkap);
            if(!matcher.find()){
                JOptionPane.showMessageDialog(this, "Pastikan kolom nama hanya huruf alphabet, Bukan angka / simbol");
                textPanel.append("Kolom nama menggunakan selain huruf alphabet\n");
                return;
            }



            if(checkUsername(username, email)){
                return;
            }


            String query = "INSERT INTO user(nama, username, email, password) VALUES(?, ?, ?, ?)";

            try(Connection connection = DriverManager.getConnection("jdbc:sqlite:tbo.db");
            PreparedStatement preparedStatement = connection.prepareStatement(query)
            ){
                preparedStatement.setString(1, namaLengkap);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);

                int hasil = preparedStatement.executeUpdate();
                if(hasil == 1){
                    JOptionPane.showMessageDialog(this, "Register Sukses, silahkan login");
                    textPanel.append("Register sukses\n");

                }else {
                    JOptionPane.showMessageDialog(this, "Register gagal");
                    textPanel.append("Register gagal\n");
                }

            }catch (Exception e){
                e.getStackTrace();
            }


        }));




        userPanel.setHasilListener(() -> {
            textPanel.append("User telah logout\n");
            addAndRemove(jTabbedPane1, jTabbedPane2, BorderLayout.CENTER);
        });

        changePassword.setCPListener((cpe) -> {
            if(cpe.getUsername().isEmpty() || cpe.getNewPassword().isEmpty() || cpe.getOldPassword().isEmpty()){
                JOptionPane.showMessageDialog(this, "Mohon isi form dengan benar");
                textPanel.append("Ada form yang kosong");
                return;
            }

            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            try(Connection connection = DriverManager.getConnection("jdbc:sqlite:tbo.db");
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, cpe.getUsername());
                preparedStatement.setString(2, cpe.getOldPassword());

                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    String query2 = "UPDATE user SET password = ? WHERE username = ?";
                    try(PreparedStatement prepareGanti = connection.prepareStatement(query2)){
                        prepareGanti.setString(1, cpe.getNewPassword());
                        prepareGanti.setString(2, cpe.getUsername());

                        int hasil = prepareGanti.executeUpdate();
                        if(hasil == 1){
                            JOptionPane.showMessageDialog(this, "Password akun "+cpe.getUsername()+" telah sukses diganti.");
                            textPanel.append("Password akun "+cpe.getUsername()+" telah diganti\n");
                        }else{
                            JOptionPane.showMessageDialog(this, "Gagal mengganti password akun");
                            textPanel.append("Password akun "+cpe.getUsername()+" gagal diganti\n");
                        }

                    }catch (Exception e){
                        System.out.println(e);
                    }
                }else {
                    JOptionPane.showMessageDialog(this, "Info akun yang anda masukkan salah");
                    textPanel.append("Info akun salah\n");
                }

                resultSet.close();

            }catch (Exception e){
                System.out.println(e);
            }
        });


        setJMenuBar(createMenu());

        add(textPanel, BorderLayout.EAST);
        add(button, BorderLayout.SOUTH);
    }

    private boolean checkUsername(String username, String email){
        Pattern pattern = Pattern.compile("\\w@[a-zA-Z]+.[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(email);

        if(!matcher.find()){
            JOptionPane.showMessageDialog(this, "Masukkan email yang valid, Cth: aldingans@tbo.com");
            textPanel.append("Email tidak valid");
            return true;
        }



        String query = "SELECT * FROM user WHERE username = ?";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:tbo.db");
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            Random random = new Random();

            String generatedString = "";
            generatedString += random.nextInt(10000);


            if(resultSet.next()){
                JOptionPane.showMessageDialog(this, "Username telah terdaftar, mungkin coba username: "+username+generatedString);
                textPanel.append("Username telah digunakan\n");
                return true;
            }

            resultSet.close();

            query = "SELECT * FROM user WHERE email = ?";

            try(PreparedStatement prepare = connection.prepareStatement(query)){
                prepare.setString(1, email);
                ResultSet hasil = prepare.executeQuery();
                if(hasil.next()){
                    JOptionPane.showMessageDialog(this, "Email telah terdaftar, gunakan email yang lain");
                    textPanel.append("Email telah digunakan\n");
                    return true;
                }

            }catch (Exception e){
                e.getStackTrace();
            }

        }catch (Exception e){
            e.getStackTrace();
        }


        return false;
    }

    private void setUpTab(){
        jTabbedPane1 = new JTabbedPane();
        jTabbedPane2 = new JTabbedPane();

        jTabbedPane1.addTab("Login", loginPanel);
        jTabbedPane1.addTab("Register", registerPanel);
        jTabbedPane1.addTab("About", about);

        jTabbedPane2.addTab("Home", userPanel);
        jTabbedPane2.addTab("Change Password", changePassword);

        add(jTabbedPane1, BorderLayout.CENTER);
    }

    private JMenuBar createMenu(){
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenu = new JMenuItem("Exit");
        fileMenu.add(exitMenu);

        JMenu layoutMenu = new JMenu("Layout");

        JMenu views = new JMenu("Views");

        JCheckBoxMenuItem logCheck = new JCheckBoxMenuItem("Enable Log");
        logCheck.setSelected(true);

        views.add(logCheck);

        layoutMenu.add(views);

        menuBar.add(fileMenu);
        menuBar.add(layoutMenu);


        logCheck.addActionListener((ev) -> {
            JCheckBoxMenuItem log = (JCheckBoxMenuItem) ev.getSource();
            textPanel.setVisible(log.isSelected());
        });

        exitMenu.addActionListener((event) -> System.exit(0));


        return menuBar;
    }

    public void addAndRemove(Component add, Component remove, Object constraint){

        remove(remove);
        add(add, constraint);
        revalidate();
        repaint();
    }
}
