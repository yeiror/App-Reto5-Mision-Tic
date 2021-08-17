package com.example.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.example.model.vo.PagadoPorProyectoVo;

public class Pagados extends AbstractTableModel {
    private List<PagadoPorProyectoVo> data;
    
    public void setData(List<PagadoPorProyectoVo> data) {
        this.data = data;
    }
      
    @Override
    public Class <?> getColumnClass(int columnIndex){
        switch(columnIndex){

            case 0:
                return Integer.class;
            case 1:
                return Integer.class;
        } 
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        switch(column){
            case 0:
                return "ID_PROYECTO";
            case 1:
                return "VALOR";
        } 
        return super.getColumnName(column);
    }

    @Override
    public int getRowCount() {
        
        return data.size();
    }

    @Override
    public int getColumnCount() {
        
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var project = data.get(rowIndex);
        switch(columnIndex){

            case 0:
                return project.getId();
            case 1:
                return project.getValor();
        } 
        return null;
        
        
    }
}
