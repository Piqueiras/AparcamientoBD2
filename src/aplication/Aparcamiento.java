/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplication;

import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class Aparcamiento {
    private String id;
    private String direccion;
    private int aforo;
    private ArrayList<PlazaAparcar> plazasAparcar;
    private ArrayList<PlazaReserva> plazasReserva;

    public Aparcamiento(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
    
}
