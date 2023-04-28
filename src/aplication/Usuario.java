/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplication;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author alumnogreibd
 */
public class Usuario {
    private String dni; //antes era char[] pero no se podía leer bien desde la base de datos
    private String nombre;
    private String telefono; //lo mismo que dni
    private String correo;
    private LocalDate fechaIngreso;
    private RolUsuario rol;
    private LocalDate fechaVeto;
    private int numeroInfracciones;
    private String contrasena;
    private ArrayList<Vehiculo> vehiculos;

    public Usuario(String dni) {
        this.dni = dni;
    }
    
    
    public Usuario(String dni, String nombre, String telefono, String correo, RolUsuario rol, int numeroInfracciones) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.rol = rol;
        this.numeroInfracciones = numeroInfracciones;
        
        this.fechaIngreso = null;
        this.fechaVeto = null;
        this.vehiculos = new ArrayList<Vehiculo>();
    }
    
    public Usuario(String dni, String nombre, String telefono, String correo, RolUsuario rol, int numeroInfracciones, String contrasena) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.rol = rol;
        this.numeroInfracciones = numeroInfracciones;
        this.contrasena = contrasena;
        
        this.fechaIngreso = null;
        this.fechaVeto = null;
        this.vehiculos = new ArrayList<Vehiculo>();
    }

    public Usuario(String dni, String nombre, String telefono, String correo, LocalDate fechaIngreso, RolUsuario rol, int numeroInfracciones, String contrasena) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaIngreso = fechaIngreso;
        this.rol = rol;
        this.numeroInfracciones = numeroInfracciones;
        this.contrasena = contrasena;
        
        this.fechaVeto = null;
        this.vehiculos = new ArrayList<Vehiculo>();
    }
     
    public Usuario(String dni, String nombre, String telefono, String correo, LocalDate fechaIngreso, RolUsuario rol, LocalDate fechaVeto, int numeroInfracciones) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaIngreso = fechaIngreso;
        this.rol = rol;
        this.fechaVeto = fechaVeto;
        this.numeroInfracciones = numeroInfracciones;
        
        this.vehiculos = new ArrayList<Vehiculo>();
    }
    
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public LocalDate getFechaVeto() {
        return fechaVeto;
    }

    public int getNumeroInfracciones() {
        return numeroInfracciones;
    }
    
    public String getContrasena(){
        return contrasena;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    
    public void setNumeroInfracciones(int numeroInfracciones) {
        this.numeroInfracciones = numeroInfracciones;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    public void setFechaVeto(LocalDate fechaVeto) {
        this.fechaVeto = fechaVeto;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void addVehiculos(Vehiculo ... variosVehiculos) {
        this.vehiculos.addAll(Arrays.asList(variosVehiculos));
    }
    
    public void addVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos.addAll(vehiculos);
    }
    
    public void incorporarVehiculos(java.util.List<Vehiculo> vehiculos){
        try {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
                vehiculo.setUsuario(this);
                this.vehiculos.add(vehiculo);
            }
        } catch (ExcepcionAparcamiento e) {}
    }

    @Override
    public String toString() {
        String res;
        res = "DNI: " + this.dni + ", Nombre: " + this.nombre + ", Rol: " + this.rol + ", Vehiculos: [";
        for (Vehiculo vehiculo : this.vehiculos) {
            res += vehiculo.toString();
        }
        res+="]";
        return res;
    }
    
    
}
