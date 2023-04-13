/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplication.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaVehiculo  extends AbstractTableModel{
    
    private List<Vehiculo> data;
    private String[] columnNames = {"Matricula", "Tipo", "Marca", "Modelo", "AnoMatriculacion"}; //OJO falta usuario y plaza

    public ModeloTablaVehiculo(){
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
            case 0: resultado=data.get(row).getMatricula(); break;
            case 1: resultado=data.get(row).getTipo();break;
            case 2: resultado=data.get(row).getMarca();break;
            case 3: resultado=data.get(row).getModelo();break;
            case 4: resultado=data.get(row).getAnoMatriculacion();break;
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
            case 3: clase=java.lang.String.class; break;
            case 4: clase=java.lang.String.class; break;
        }
        return clase;
    }
    
    public void setFilas(java.util.List<Vehiculo> v){
        this.data=v;
        fireTableDataChanged();
    }
    
        public Vehiculo obtenerPlazaReservar(int i){
        return this.data.get(i);
    }
}
