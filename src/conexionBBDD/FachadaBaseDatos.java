/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexionBBDD;

//import aplication.<nombreClase>;
import aplication.Aparcar;
import aplication.PlazaAparcar;
import aplication.PlazaReserva;
import aplication.Reservar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import aplication.Usuario;
import aplication.Vehiculo;
import java.util.List;
import conexionBBDD.DAOUsuarios;
import java.util.Date;

/**
 *
 * @author basesdatos
 */
public class FachadaBaseDatos {
    private aplication.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOUsuarios daoUsuarios;
    private DAOPlazas daoPlazas;

    //private DAOClase daoClase;
    
    public FachadaBaseDatos (aplication.FachadaAplicacion fa){
        
        Properties configuracion = new Properties();
        this.fa=fa;
        FileInputStream arqConfiguracion;

        try {
            //Class.forName("postgresql.jar");
            
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();
     
            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"),
                    usuario);
            
            daoUsuarios = new DAOUsuarios(conexion, fa);
            daoPlazas = new DAOPlazas(conexion, fa);

            
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
        //} catch (ClassNotFoundException e) {        //anhadido por mi para usar forName()
        //    System.out.println(e.getMessage());
        //    fa.muestraExcepcion(e.getMessage());
        } catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
        }
    }

    public Usuario validarUsuario(String dni){
        return daoUsuarios.validarUsuario(dni);
    }
    
    public List<Aparcar> historialAparcar (String dni, String mat, String pza, String ap, String cMax, String cMin, String dMax, String dMin, String fMax, String fMin) {
        return daoUsuarios.ConsultarHistorialAparcar(dni, mat, pza, ap, cMax, cMin, dMax, dMin, fMax, fMin);
    }
    
    public List<Reservar> historialReservar (String dni, String mat, String pza, String ap, String cMax, String cMin, String dMax, String dMin, String fMax, String fMin) {
        return daoUsuarios.ConsultarHistorialReservar(dni, mat, pza, ap, cMax, cMin, dMax, dMin, fMax, fMin);
    }
    
     
    public List<PlazaReserva> obtenerPlazasReserva(String codigoAparcamiento, Integer  codigoPlaza, String tipoPlaza, boolean ocupadas){
        return daoPlazas.obtenerPlazasReserva(codigoAparcamiento,codigoPlaza,tipoPlaza,ocupadas);
    }
    
    public boolean registrarUsuario(Usuario usuario){
        return daoUsuarios.registrarUsuario(usuario);
    }
    
    public List<PlazaAparcar> obtenerPlazasAparcar(String codigoAparcamiento, Integer  codigoPlaza, String tipoPlaza, boolean ocupadas){
        return daoPlazas.obtenerPlazasAparcar(codigoAparcamiento,codigoPlaza,tipoPlaza,ocupadas);
    }

    public List<Vehiculo> obtenerVehiculos() {
        return daoUsuarios.obtenerVehiculos();
    }
    public List<Vehiculo> obtenerVehiculos(String dni) {
        return daoUsuarios.obtenerVehiculos(dni);
    }
    
    public List<Vehiculo> obtenerVehiculosNoAparcados(){
        return daoUsuarios.obtenerVehiculosNoAparcados();
    }
    
    public List<Vehiculo> obtenerVehiculosNoAparcados(String dni){
        return daoUsuarios.obtenerVehiculosNoAparcados(dni);
    }

    public boolean hacerReserva(String matricula, int plaza, String aparcamiento,  Date horaInicio, Date horaFin) {
        return daoPlazas.hacerReserva(matricula, plaza, aparcamiento, horaInicio,horaFin);
    }
    
    public String obtenerRol(String dni){
        return daoUsuarios.obtenerRol(dni);
    }
    
    public boolean Aparcar(String matricula, int plaza, String aparcamiento) {
        return daoPlazas.Aparcar(matricula,plaza,aparcamiento);
    }
    
    public boolean anadirPlaza(String codigoAparcamiento, int codigoPlaza, String tipoPlaza) {
        return daoPlazas.anadirPlaza(codigoAparcamiento,codigoPlaza,tipoPlaza);
    }
    
    public boolean anadirPlazaReserva(String codigoAparcamiento, int codigoPlaza, String tipoPlaza) {
        return daoPlazas.anadirPlazaReserva(codigoAparcamiento, codigoPlaza, tipoPlaza);
    }
    
    public boolean eliminarPlazaAparcar(int codigoAparcamiento){
        return daoPlazas.eliminarPlazaAparcar(codigoAparcamiento);
    }
    
    public boolean eliminarPlazaReserva(int codigoAparcamiento){
        return daoPlazas.eliminarPlazaReserva(codigoAparcamiento);
    }
     /**
     * 
     * @param dni 
     * Debería registrar unha infraccion e no caso de ter cinco, deberíase poñer un veto.
     * Devolve o numero de infracciones desde que si quitou, no caso de telo, o último veto
     */
    public int registrarInfraccion(String dni){
        this.daoUsuarios.registrarInfraccion(dni);
        if(this.daoUsuarios.mostrarNumeroInfracciones(dni)==5){
            this.daoUsuarios.registrarVeto(dni);
            this.daoUsuarios.quitarTodasInfracciones(dni);
            return 5;
        }
        return this.daoUsuarios.mostrarNumeroInfracciones(dni);
    }
    /**
     * Hai que implementar un método que sexa actualizar no que cada vez que se abra a aplicacion, 
     * se comprobe cantos dias pasaron do veto e ao cumplir x dias, pois facer un quitarVeto e quitarInfracciones
     */
    public Usuario obtenerUsuario(String matricula){
        return this.daoUsuarios.obtenerUsuario(matricula);
    }
    public int mostrarNumeroInfracciones(String dni){
        return this.daoUsuarios.mostrarNumeroInfracciones(dni);
    }
}
