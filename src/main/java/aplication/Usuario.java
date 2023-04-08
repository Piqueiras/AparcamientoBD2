/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplication;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class Usuario {
    private char[] dni; //son 9 caracteres
    private String nombre;
    private char[] telefono;
    private String correo;
    private LocalDate fechaIngreso;
    private RolUsuario rol;
    private LocalDate fechaVeto;
    private int numeroInfracciones;
    private ArrayList<Vehiculo> vehiculos;
    
}
