package com.projecttbo.gui;

import javax.swing.*;
import java.awt.*;

public class TextPanel  extends JPanel {
    JTextArea textArea;

    public TextPanel(){
        this.textArea = new JTextArea();
        setBorder(BorderFactory.createTitledBorder("Log"));
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea));
        setPreferredSize(new Dimension(300, 300));
    }


    public void append(String text){
        this.textArea.append(text);
    }

}
