package com.example.view;

import javax.swing.JFrame;

public class FrmProyecto extends JFrame{
    public FrmProyecto() {
        initGUI();
        setLocationRelativeTo(null);
        
    }
    private void initGUI(){
        setSize(800,600);
        setTitle("Reto 5 - Misión TIC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
