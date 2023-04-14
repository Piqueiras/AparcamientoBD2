/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.time.LocalDate;
import aplication.Usuario;
import javax.swing.table.*;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaUsuarios extends AbstractTableModel {
    private java.util.List<Usuario> usuarios;
    public ModeloTablaUsuarios(){
        this.usuarios = new java.util.ArrayList<Usuario>();
    }
    public int getColumnCount (){
        return 8;
    }
    public int getRowCount(){
        return usuarios.size();
    }
    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "dni"; break;
            case 1: nombre="nombre"; break;
            case 2: nombre= "telefono"; break;
            case 3: nombre="correo"; break;
            case 4: nombre="fechaingresousc"; break;
            case 5: nombre="rol"; break;
            case 6: nombre="fechaveto";break;
            case 7: nombre="numeroinfracciones";break;
        }
        return nombre;
    }
     @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.Character.class; break;
            case 1: clase= java.lang.String.class; break;
            case 2: clase=java.lang.Character.class; break;
            case 3: clase=java.lang.String.class; break;
            case 4: clase=java.time.LocalDate.class; break;
            case 5: clase=aplication.RolUsuario.class; break;
            case 6: clase=java.time.LocalDate.class; break;
            case 7: clase=java.lang.Integer.class; break;
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
            case 0: resultado= usuarios.get(row).getDni(); break;
            case 1: resultado= usuarios.get(row).getNombre(); break;
            case 2: resultado=usuarios.get(row).getTelefono();break;
            case 3: resultado=usuarios.get(row).getCorreo(); break;
            case 4: resultado=usuarios.get(row).getFechaIngreso(); break;
            case 5: resultado=usuarios.get(row).getRol(); break;
            case 6: resultado=usuarios.get(row).getFechaVeto(); break;
            case 7: resultado=usuarios.get(row).getNumeroInfracciones(); break;
        }
        return resultado;
    }
       public void setFilas(java.util.List<Usuario> usuarios){
        this.usuarios=usuarios;
        fireTableDataChanged();
    }

    public Usuario obtenerUsuario(int i){
        return this.usuarios.get(i);
    }
}
