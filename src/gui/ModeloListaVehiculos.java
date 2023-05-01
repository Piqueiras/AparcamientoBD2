/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import aplication.Vehiculo;

/**
 *
 * @author basesdatos
 */
public class ModeloListaVehiculos extends javax.swing.AbstractListModel {
    java.util.List<Vehiculo> elementos;

    public ModeloListaVehiculos(){
        this.elementos=new java.util.ArrayList<Vehiculo>();
    }

    public int getSize(){
        return this.elementos.size();
    }

    public Vehiculo getElementAt(int i){
        return elementos.get(i);
    }

    public void nuevoElemento(Vehiculo e){
        if (!this.elementos.contains(e)) {
            this.elementos.add(e);
            fireIntervalAdded(this, this.elementos.size()-1, this.elementos.size()-1);
        }
        
    }

    public void borrarElemento(int i){
        Vehiculo e;
        e=this.elementos.get(i);
        this.elementos.remove(i);
        fireIntervalRemoved(this,i,i);
    }

    public void setElementos(java.util.List<Vehiculo> elementos){
        this.elementos=elementos;
        fireContentsChanged(this, 0, elementos.size()-1);
    }

    public java.util.List<Vehiculo> getElementos(){
        return this.elementos;
    }
}
