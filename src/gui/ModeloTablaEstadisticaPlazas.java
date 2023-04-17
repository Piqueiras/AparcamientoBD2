/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaEstadisticaPlazas extends AbstractTableModel {

    private List<HashMap<String, Object>> data;
    private String[] columnNames = {"Plaza", "TIpo","Veces"}; 

    public ModeloTablaEstadisticaPlazas() {
        this.data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        if(data==null) return 0;
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
    public Class getColumnClass(int col) {
        Class clase = null;

        switch (col) {
            case 0:
                clase = java.lang.Integer.class;
                break;
            case 1:
                clase = java.lang.String.class;
                break;
            case 2:
                clase = java.lang.Integer.class;
                break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object resultado = null;
        
        
     

        switch (col) {
            case 0:
                resultado = data.get(row).get("plaza");
                break;
            case 1:
                resultado = data.get(row).get("tipo");
                break;
            case 2:
                resultado = data.get(row).get("veces");
                break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<HashMap<String, Object>> datos) {
        this.data = datos;
        fireTableDataChanged();
        
        
       
    }

    public HashMap<String, Object> obtenerDato(int i) {
        return this.data.get(i);
    }
}
