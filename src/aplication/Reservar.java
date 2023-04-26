/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplication;

import java.time.LocalDateTime;
import java.time.Duration;
        
/**
 * El objetivo de esta clase es plasmar la tabla 'reservar'. Se usa en la gestion de pagos.
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
    private RolUsuario rol;

    public Reservar(Vehiculo vehiculo, Integer codigoPlaza, String idAparcamiento, LocalDateTime fechaEntrada, RolUsuario rol) {
        this.vehiculo = vehiculo;
        this.codigoPlaza = codigoPlaza.toString();
        this.idAparcamiento = idAparcamiento;
        this.fechaEntrada = fechaEntrada;
        this.rol = rol;
        
        this.fechaSalida = null;
        this.dias = 0;
        this.horas = 0;
        this.minutos = 0;
        this.segundos = 0;
        
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

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
        
        //Aprovechamos para actualizar la duracion y el coste
        if(this.fechaEntrada != null && this.fechaSalida != null) {
            this.dias = (int) Duration.between(fechaEntrada, fechaSalida).toDays();
            this.horas = (int) Duration.between(fechaEntrada, fechaSalida).toHours() % 24;
            this.minutos = (int) (Duration.between(fechaEntrada, fechaSalida).toMinutes() % 60);
            this.segundos = (int) (Duration.between(fechaEntrada, fechaSalida).toSeconds() % 60);        
        
            actualizarPrecio(rol);
        }
    }
    
    /**
     * Este metodo actualiza el precio de la reserva
     * @param rol - Rol del usuario. El precio dependera de si pertenece a la USC o no
     */
    public final void actualizarPrecio(RolUsuario rol) {
        double mult = 7.5d;
        if (rol.equals(RolUsuario.noUSC)) {
            mult = 10d;
        }
        if (this.fechaSalida == null) {
            this.dias = (int) Duration.between(fechaEntrada, LocalDateTime.now()).toDays();
            this.horas = (int) Duration.between(fechaEntrada, LocalDateTime.now()).toHours() % 24;
            this.minutos = (int) Duration.between(fechaEntrada, LocalDateTime.now()).toMinutes() % 60;
            this.segundos = (int) Duration.between(fechaEntrada, LocalDateTime.now()).toSeconds() % 60;
        }        
        precio = (double) dias * mult;
            if (horas > 0 || minutos > 0 || segundos > 0) {
                precio += (double) mult;
        
        }
    }
}
