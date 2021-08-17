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
    private JTable tabla,tabla2,tabla3;

    public FrmConsultas() {
        
        controllerReport = new ReportesController();
        tabla = new JTable();
        tabla2 = new JTable(); 
        tabla3 = new JTable(); 
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

    //---------------------------- Segundo Módulo ----------------
    var panelPagados = new JPanel();
    panelPagados.setLayout(new BorderLayout());
    tbd.addTab("Consulta Total Gastado por Proyectos", panelPagados);

    var panelEntrada2= new JPanel();
    panelEntrada2.add(new JLabel("Superior a: "));
    var txtPagado = new JTextField(15);
    panelEntrada2.add(txtPagado);

    //JButton
    var btnPagados = new JButton("Consultar");
    //Evento del JButton
    btnPagados.addActionListener(e -> listarpagados(
    txtPagado.getText().trim()));
    
    panelEntrada2.add(btnPagados);
    panelPagados.add(panelEntrada2, BorderLayout.PAGE_START);

    panelPagados.add(new JScrollPane(tabla2),BorderLayout.CENTER);
        
    }

//---------------- Métodos de Eventos de Botones

    private void listarpagados(String pagado) {
        Double pagadoDouble=0D;
        pagadoDouble=Double.parseDouble(pagado);

        try {
            var lista = controllerReport.listarTotalPagado(pagadoDouble);
            var tableModel = new Pagados();
            tableModel.setData(lista);
            tabla2.setModel(tableModel);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
            
        }
        
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
