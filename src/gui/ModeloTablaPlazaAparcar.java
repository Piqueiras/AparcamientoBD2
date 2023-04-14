/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplication.PlazaAparcar;
import aplication.PlazaReserva;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaPlazaAparcar extends AbstractTableModel {

    private List<PlazaAparcar> data;
    private String[] columnNames = {"Aparcamiento", "Código Plaza", "Tipo Plaza"};

    public ModeloTablaPlazaAparcar(){
            this.data= new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado=data.get(row).getAparcamiento().getId(); break;
            case 1: resultado=data.get(row).getCodigo(); break;
            case 2: resultado=data.get(row).getTipo();break;
        }
        return resultado;
    }
    
        @Override
    public Class getColumnClass(int col){
        Class clase=null;
        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase=java.lang.String.class; break;
        }
        return clase;
    }
    
    public void setFilas(java.util.List<PlazaAparcar> plazas){
        this.data=plazas;
        fireTableDataChanged();
    }
    
        public PlazaAparcar obtenerPlazaAparcar(int i){
        return this.data.get(i);
    }

}

