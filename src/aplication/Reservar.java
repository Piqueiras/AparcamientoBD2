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
public class Reservar {
    private Vehiculo vehiculo;
    private String codigoPlaza;
    private String idAparcamiento;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private int dias;
    private int horas;
    private int minutos;
    private int segundos;
    private Double precio;

    public Reservar(Vehiculo vehiculo, Integer codigoPlaza, String idAparcamiento, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, RolUsuario rol) {
        this.vehiculo = vehiculo;
        this.codigoPlaza = codigoPlaza.toString();
        this.idAparcamiento = idAparcamiento;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        
        this.dias = (int) Duration.between(fechaEntrada, fechaSalida).toDays();
        this.horas = (int) Duration.between(fechaEntrada, fechaSalida).toHours() % 24;
        this.minutos = (int) (Duration.between(fechaEntrada, fechaSalida).toMinutes() % 60);
        this.segundos = (int) (Duration.between(fechaEntrada, fechaSalida).toSeconds() % 60);        
        
        actualizarPrecio(rol);
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
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
    
    public int getDias() {
        return dias;
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
    
    public final void actualizarPrecio(RolUsuario rol) {
        double mult = 7.5d;
        if (rol.equals(RolUsuario.noUSC)) {
            mult = 10d;
        }
        precio = (double) dias * mult;
            if (horas > 0 || minutos > 0 || segundos > 0) {
                precio += (double) mult;
        }
    }
}
