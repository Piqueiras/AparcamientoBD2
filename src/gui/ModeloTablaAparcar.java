/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplication.Aparcar;
import javax.swing.table.*;
/**
 * El modelo de la JTable de aparcamientos que se muestra en la ventana VPagos
 * @author basesdatos
 */
public class ModeloTablaAparcar extends AbstractTableModel{
    private java.util.List<Aparcar> aparcamientos;

    public ModeloTablaAparcar(){
        this.aparcamientos=new java.util.ArrayList<Aparcar>();
    }

    public int getColumnCount (){
        return 8;
    }

    public int getRowCount(){
        return aparcamientos.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre="DNI"; break;
            case 1: nombre="Matrícula"; break;
            case 2: nombre="Plaza"; break;
            case 3: nombre="Aparcamiento"; break;
            case 4: nombre="Entrada"; break;
            case 5: nombre="Salida"; break;
            case 6: nombre="Duración"; break;
            case 7: nombre="Coste"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase=java.lang.String.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase= java.lang.String.class; break;
            case 3: clase=java.lang.String.class; break;
            case 4: clase=java.lang.String.class; break;
            case 5: clase=java.lang.String.class; break;
            case 6: clase=java.lang.String.class; break;
            case 7: clase=java.lang.String.class; break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado=aparcamientos.get(row).getVehiculo().getUsuario().getDni(); break;
            case 1: resultado=aparcamientos.get(row).getVehiculo().getMatricula(); break;
            case 2: resultado=aparcamientos.get(row).getCodigoPlaza(); break;
            case 3: resultado=aparcamientos.get(row).getIdAparcamiento();break;
            case 4: resultado=aparcamientos.get(row).getFechaEntrada().toString().replaceAll("T", " "); break;
            case 5: if(aparcamientos.get(row).getFechaSalida() != null) {
                        resultado=aparcamientos.get(row).getFechaSalida().toString().replaceAll("T", " ");
                    } else {
                        resultado=null;
                    } break;
            case 6: resultado=aparcamientos.get(row).getHoras() + " h, " + aparcamientos.get(row).getMinutos() + "min y " + aparcamientos.get(row).getSegundos() + " s"; break;
            case 7: resultado=aparcamientos.get(row).getPrecio().toString() + " €"; break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Aparcar> aparcamientos){
        this.aparcamientos=aparcamientos;
        fireTableDataChanged();
    }

    public Aparcar obtenerAparcar(int i){
        return this.aparcamientos.get(i);
    }

}
