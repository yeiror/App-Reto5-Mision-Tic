package com.example.view;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import com.example.view.ProyectoBanco;

import com.example.controller.ReportesController;


public class FrmConsultas extends JFrame {
    private ReportesController controllerReport;
    private JTable tabla;

    public FrmConsultas() {
        
        controllerReport = new ReportesController();
        tabla = new JTable(); 
        initGUI();
        setLocationRelativeTo(null);
        
    }
    private void initGUI(){
        setSize(1200,650);
        setTitle("Reto 5 - Misión TIC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var tbd = new JTabbedPane();
        getContentPane().add(tbd, BorderLayout.CENTER);

        var panelLider = new JPanel();
        panelLider.setLayout(new BorderLayout());
        tbd.addTab("Consulta Proyectos por Clasificaciones", panelLider);

        var panelEntrada= new JPanel();
        panelEntrada.add(new JLabel("Banco"));
        var txtBanco = new JTextField(15);
        panelEntrada.add(txtBanco);

        //JButton
        var btnConsultaLider = new JButton("Consultar");
        //Evento del JButton
        btnConsultaLider.addActionListener(e -> listarporBancos(
        txtBanco.getText().trim()));
        
        
        panelEntrada.add(btnConsultaLider);
        panelLider.add(panelEntrada, BorderLayout.PAGE_START);

        panelLider.add(new JScrollPane(tabla),BorderLayout.CENTER);

    }
    private void listarporBancos(String opc1)
    {
        try {
            var lista = controllerReport.listadoProyectosBancos(opc1);
            var tableModel = new ProyectoBanco();
            tableModel.setData(lista);
            tabla.setModel(tableModel);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
            
        }
    }


}
