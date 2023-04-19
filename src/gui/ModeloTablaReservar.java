/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplication.Reservar;
import javax.swing.table.*;
/**
 *
 * @author basesdatos
 */
public class ModeloTablaReservar extends AbstractTableModel{
    private java.util.List<Reservar> reservas;

    public ModeloTablaReservar(){
        this.reservas=new java.util.ArrayList<Reservar>();
    }

    public int getColumnCount (){
        return 8;
    }

    public int getRowCount(){
        return reservas.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre="DNI"; break;
            case 1: nombre="Matrícula"; break;
            case 2: nombre="Plaza"; break;
            case 3: nombre="Aparcamiento"; break;
            case 4: nombre="Inicio"; break;
            case 5: nombre="Fin"; break;
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
            case 0: resultado=reservas.get(row).getVehiculo().getUsuario().getDni(); break;
            case 1: resultado=reservas.get(row).getVehiculo().getMatricula(); break;
            case 2: resultado=reservas.get(row).getCodigoPlaza(); break;
            case 3: resultado=reservas.get(row).getIdAparcamiento();break;
            case 4: resultado=reservas.get(row).getFechaEntrada().toString(); break;
            case 5: if(reservas.get(row).getFechaSalida() != null) {
                        resultado=reservas.get(row).getFechaSalida().toString().replaceAll("T", " ");
                    } else {
                        resultado=null;
                    } break;
            case 6: resultado=reservas.get(row).getDias() + " d, " + reservas.get(row).getHoras() + " h, " + reservas.get(row).getMinutos() + "min y " + reservas.get(row).getSegundos() + " s"; break;
            case 7: resultado=reservas.get(row).getPrecio().toString() + " €"; break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Reservar> reservas){
        this.reservas=reservas;
        fireTableDataChanged();
    }

    public Reservar obtenerReservar(int i){
        return this.reservas.get(i);
    }

}
