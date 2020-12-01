package com.projecttbo.gui;

import javax.swing.*;

public class Main {
    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = null;
            try {
                mainFrame = new MainFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert mainFrame != null;
            mainFrame.setVisible(true);
        });




    }
}
