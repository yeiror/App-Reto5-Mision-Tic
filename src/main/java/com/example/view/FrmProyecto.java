package com.example.view;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import com.example.controller.ConsultasController;
import com.example.controller.ReportesController;
import com.example.model.vo.ProyectosConsultaVo;


public class FrmProyecto extends JFrame{

    private ConsultasController controller;
    private ReportesController controllerReport;
    private JTable tabla;


    public FrmProyecto() {
        controller=new ConsultasController();
        controllerReport = new ReportesController(); 
        initGUI();
        setLocationRelativeTo(null);
        
    }
    private void initGUI(){
        setSize(1200,650);
        setTitle("Reto 5 - Misión TIC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var tbd = new JTabbedPane();
        getContentPane().add(tbd, BorderLayout.CENTER);

        var panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tbd.addTab("Consulta Proyectos por Clasificaciones", panel);

        //label y textfield 1
        var panelEntrada= new JPanel();
        panelEntrada.add(new JLabel("Clasificación 1"));
        var txtClasificacion1 = new JTextField(15);
        panelEntrada.add(txtClasificacion1);

        //label y textfield 2
        panelEntrada.add(new JLabel("Clasificación 2"));
        var txtClasificacion2 = new JTextField(15);
        panelEntrada.add(txtClasificacion2);

        //Jbutton
        var btnConsulta = new JButton("Consultar");
        //Evento del JButton
        btnConsulta.addActionListener(e -> consultarProyectosporClasificacion(
        txtClasificacion1.getText().trim(), 
        txtClasificacion2.getText().trim()));
    
        
            panelEntrada.add(btnConsulta);
        panel.add(panelEntrada, BorderLayout.PAGE_START);
        

        //JTable
        tabla = new JTable();
        panel.add(new JScrollPane(tabla),BorderLayout.CENTER);

    }
    private void consultarProyectosporClasificacion(String opc1, String opc2)
    {
        try {
            var lista = controller.listadoProyectosDeClasificaciones(opc1, opc2);
            var tableModel = new ProyectosTableModel();
            tableModel.setData(lista);
            tabla.setModel(tableModel);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
            
        }
    }

    private class ProyectosTableModel extends AbstractTableModel{
        private List<ProyectosConsultaVo> data;
        
        public void setData(List<ProyectosConsultaVo> data) {
            this.data = data;
        }
          
        @Override
        public Class <?> getColumnClass(int columnIndex){
            switch(columnIndex){

                case 0:
                    return String.class;
                case 1:
                    return String.class;
                case 2:
                    return Integer.class;
                case 3:
                    return Integer.class;
                case 4:
                    return String.class;
            } 
            return super.getColumnClass(columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            // TODO Auto-generated method stub
            switch(column){

                case 0:
                    return "Ciudad";
                case 1:
                    return "Clasificacion";
                case 2:
                    return "Total";
                case 3:
                    return "Viejo";
                case 4:
                    return "Reciente";
            } 
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            
            return data.size();
        }

        @Override
        public int getColumnCount() {
            
            return 5;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var project = data.get(rowIndex);
            switch(columnIndex){

                case 0:
                    return project.getCiudad();
                case 1:
                    return project.getClasificacion();
                case 2:
                    return project.getTotal();
                case 3:
                    return project.getViejo();
                case 4:
                    return project.getReciente();
            } 
            return null;
            
            
        }
    }
}
