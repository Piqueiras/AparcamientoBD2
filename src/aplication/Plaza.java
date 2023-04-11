/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplication;

/**
 *
 * @author alumnogreibd
 */
public abstract class Plaza {
    private int codigo;
    private TipoPlaza tipo;
    private Aparcamiento aparcamiento;
    private Vehiculo vehiculo;

    public Plaza(int codigo, TipoPlaza tipo, Aparcamiento aparcamiento) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.aparcamiento = aparcamiento;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public TipoPlaza getTipo() {
        return tipo;
    }

    public Aparcamiento getAparcamiento() {
        return aparcamiento;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    
    
    
}