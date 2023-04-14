/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author alumnogreibd
 */
public class ModeloListaStrings extends javax.swing.AbstractListModel {
    java.util.List<String> elementos;

    public ModeloListaStrings(){
        this.elementos=new java.util.ArrayList<String>();
    }

    public int getSize(){
        return this.elementos.size();
    }

    public String getElementAt(int i){
        return elementos.get(i);
    }

    public void nuevoElemento(String e){
        this.elementos.add(e);
        fireIntervalAdded(this, this.elementos.size()-1, this.elementos.size()-1);
    }

    public void borrarElemento(int i){
        String e;
        e=this.elementos.get(i);
        this.elementos.remove(i);
        fireIntervalRemoved(this,i,i);
    }

    public void setElementos(java.util.List<String> elementos){
        this.elementos=elementos;
        fireContentsChanged(this, 0, elementos.size()-1);
    }

    public java.util.List<String> getElementos(){
        return this.elementos;
    }
}
