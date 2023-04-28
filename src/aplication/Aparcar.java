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
    private Vehiculo vehiculo;
    private String codigoPlaza;
    private String idAparcamiento;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Duration duracion;
    private Double precio;
    private RolUsuario rol;

    public Aparcar(Vehiculo vehiculo, Integer codigoPlaza, String idAparcamiento, LocalDateTime fechaEntrada, RolUsuario rol) {
        this.vehiculo = vehiculo;
        this.codigoPlaza = codigoPlaza.toString();
        this.idAparcamiento = idAparcamiento;
        this.fechaEntrada = fechaEntrada;
        this.rol = rol;
        
        this.fechaSalida = null;
        this.duracion = null;
        
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

    public Duration getDuracion() {
        return duracion;
    }
    
    public Double getPrecio() {
        return precio;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    public void actualizarFechaSalida(LocalDateTime fechaSalida) {
        this.setFechaSalida(fechaSalida);
        
        //Actualizamos la duracion y el precio
        if(this.fechaEntrada != null && this.fechaSalida != null){
            this.duracion = Duration.between(this.fechaEntrada, this.fechaSalida);
        
            actualizarPrecio(this.rol);
        }
    }
    
    /**
     * Este metodo actualiza el precio del aparcamiento
     * @param rol - Rol del usuario. El precio dependera de si pertenece a la USC o no
     */
    public final void actualizarPrecio(RolUsuario rol) {
        double mult = 0.5d;
        if (rol.equals(RolUsuario.noUSC)) {
            mult = 1d;
        }
        if (this.fechaSalida == null) {
            this.duracion = Duration.between(this.fechaEntrada, LocalDateTime.now());
        }
        precio = (double) this.duracion.toHoursPart() * mult;
            if (this.duracion.toMinutesPart() > 0 || this.duracion.toSecondsPart() > 0) {
                precio += (double) mult;
        }
    }
}