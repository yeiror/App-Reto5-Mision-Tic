package com.example.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.example.model.vo.ProyectoBancoVo;


public class ProyectoBanco extends AbstractTableModel{
    private List<ProyectoBancoVo> data;
    
    public void setData(List<ProyectoBancoVo> data) {
        this.data = data;
    }
      
    @Override
    public Class <?> getColumnClass(int columnIndex){
        switch(columnIndex){

            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Integer.class;
            case 5:
                return String.class;
        } 
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        switch(column){

            case 0:
                return "ID";
            case 1:
                return "CONSTRUCTORA";
            case 2:
                return "CIUDAD";
            case 3:
                return "CLASIFICACION";
            case 4:
                return "ESTRATO";
            case 5:
                return "LIDER";
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
                return project.getId();
            case 1:
                return project.getConstructora();
            case 2:
                return project.getCiudad();
            case 3:
                return project.getClasificacion();
            case 4:
                return project.getEstrato();
            case 5:
                return project.getLider();

        } 
        return null;
        
        
    }
}