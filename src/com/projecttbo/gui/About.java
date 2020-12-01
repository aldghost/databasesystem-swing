package com.projecttbo.gui;

import javax.swing.*;
import java.awt.*;

public class About extends JPanel {

    private JLabel aldinLabel, gagasLabel;

    public About(){
        aldinLabel = new JLabel("Aldin Gans / 1402019011");
        gagasLabel = new JLabel("Nazhifah / 1402019079");

        setLayout();
    }

    private void setLayout(){
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        addGBC(gbc, 0, 0, GridBagConstraints.CENTER);
        add(aldinLabel, gbc);


        gbc.weightx = 1;
        gbc.weighty = 1;
        addGBC(gbc, 0, 1, GridBagConstraints.CENTER);
        add(gagasLabel, gbc);

    }
    public void addGBC(GridBagConstraints gbc, int gridX, int gridY, int anchor){
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.anchor = anchor;
    }
}
