/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplication;

import java.time.LocalDateTime;
import java.time.Duration;
        
/**
 * El objetivo de esta clase es plasmar la tabla 'aparcar'. Se usa en la gestion de pagos.
 * @author alumnogreibd
 */
public class Aparcar {
    private String matriculaVehiculo;
    private String codigoPlaza;
    private String idAparcamiento;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private int horas;
    private int minutos;
    private int segundos;
    private Double precio;

    public Aparcar(String matriculaVehiculo, Integer codigoPlaza, String idAparcamiento, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, FachadaAplicacion fa) {
        this.matriculaVehiculo = matriculaVehiculo;
        this.codigoPlaza = codigoPlaza.toString();
        this.idAparcamiento = idAparcamiento;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        
        this.horas = (int) Duration.between(fechaEntrada, fechaSalida).toHours();
        this.minutos = (int) (Duration.between(fechaEntrada, fechaSalida).toMinutes() % 60);
        this.segundos = (int) (Duration.between(fechaEntrada, fechaSalida).toSeconds() % 60);        
        
        actualizarPrecio(fa);
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public String getCodigoPlaza() {
        return codigoPlaza;
    }

    public String getIdAparcamiento() {
        return idAparcamiento;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public Double getPrecio() {
        return precio;
    }
    
    public final void actualizarPrecio(FachadaAplicacion fa) {
        double mult = 0.5d;
        if (fa.getUsuario().getRol().equals(RolUsuario.noUSC)) {
            mult = 1d;
        }
        precio = (double) horas * mult;
            if (minutos > 0 || segundos > 0) {
                precio += (double) mult;
        }
    }
}
