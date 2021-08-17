package com.example.view.tableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.example.model.vo.ComprasDeLiderVo;


public class Lideres extends AbstractTableModel {
    private List<ComprasDeLiderVo> data;
    
    public void setData(List<ComprasDeLiderVo> data) {
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
                return "LIDER";
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
                return project.getLider();
            case 1:
                return project.getValor();
        } 
        return null;
        
        
    }
    
}
