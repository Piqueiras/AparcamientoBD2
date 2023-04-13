/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplication;

/**
 *
 * @author alumnogreibd
 */
public class Vehiculo {
    private String matricula;
    private TipoPlaza tipo;
    private String marca;
    private String modelo;
    private Integer anoMatriculacion;
    private Usuario usuario;
    private Plaza plaza;

    public Vehiculo(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getAnoMatriculacion() {
        return anoMatriculacion;
    }

    public String getModelo() {
        return modelo;
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public TipoPlaza getTipo() {
        return tipo;
    }
    
    
    
    
}
