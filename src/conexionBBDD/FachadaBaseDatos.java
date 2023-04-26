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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
        } catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
        }
    }

    public DAOUsuarios getDaoUsuarios() {
        return daoUsuarios;
    }

    /**
     * Este metodo es llamado desde la fachada de la aplicacion por comprobarAutentificacion(), el cual se ejecuta al principio del programa.
     * Forma parte del camino hasta el metodo encargado de comprobar que el dni introducido es de un administrador.
     * @param dni - dni a verificar
     * @return Usuario - Si el usuario esta en la base de datos y es un administrador, se devuelve una instancia de Usuario con sus datos
     */
    public Usuario validarUsuario(String dni){
        return daoUsuarios.validarUsuario(dni);
    }
    
    /** 
     * Este metodo es llamado por historialAparcar() de FachadaAplicacion.
     * Llama al metodo que obtiene todas las tuplas de la tabla Aparcar de la base de datos que cumplan las condiciones especificadas.
     * @param dni - DNI del usuario cuyos aparcamientos se quieren obtener.
     * @param mat - Matricula del vehiculo cuyos aparcamientos se quieren obtener.
     * @param pza - Numero de la plaza cuyos aparcamientos se quieren obtener.
     * @param ap - Id del aparcamiento cuyos aparcamientos se quieren obtener.
     * @param cMax - Coste maximo de los aparcamientos.
     * @param cMin - Coste minimo de los aparcamientos.
     * @param dMax - Duracion maxima de los aparcamientos.
     * @param dMin - Duracion minima de los aparcamientos.
     * @param fMax - Fecha maxima de los aparcamientos.
     * @param fMin - Fecha minima de los aparcamientos.
     * @return List<Aparcar> - Lista con los datos de las tuplas de la tabla Aparcar almacenados en instancias de Aparcar.
     */
    public List<Aparcar> historialAparcar (String dni, String mat, String pza, String ap, String cMax, String cMin, String dMax, String dMin, String fMax, String fMin) {
        return daoUsuarios.ConsultarHistorialAparcar(dni, mat, pza, ap, cMax, cMin, dMax, dMin, fMax, fMin);
    }
    
    /**
     * Este metodo es llamado por historialReservar() de FachadaAplicacion.
     * Llama al metodo que obtiene todas las tuplas de la tabla Reservar de la base de datos que cumplan las condiciones especificadas.
     * @param dni - DNI del usuario cuyas reservas se quieren obtener.
     * @param mat - Matricula del vehiculo cuyas reservas se quieren obtener.
     * @param pza - Numero de la plaza cuyas reservas se quieren obtener.
     * @param ap - Id del aparcamiento cuyas reservas se quieren obtener.
     * @param cMax - Coste maximo de las reservas.
     * @param cMin - Coste minimo de las reservas.
     * @param dMax - Duracion maxima de las reservas.
     * @param dMin - Duracion minima de las reservas.
     * @param fMax - Fecha maxima de las reservas.
     * @param fMin - Fecha minima de las reservas.
     * @return List<Reservar> - Lista con los datos de las tuplas de la tabla Aparcar almacenados en instancias de Aparcar.
     */
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
    
    public boolean eliminarPlazaAparcar(int codigoAparcamiento) throws SQLException{
        try{
            return daoPlazas.eliminarPlazaAparcar(codigoAparcamiento);
        }catch(SQLException e){
            throw e;
        }
    }
    
    public boolean eliminarPlazaReserva(int codigoAparcamiento) throws SQLException{
        try{
            return daoPlazas.eliminarPlazaReserva(codigoAparcamiento);
        }catch(SQLException e){
            throw e;
        }
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
    
    public Usuario obtenerUsuario(String matricula){
        return this.daoUsuarios.obtenerUsuario(matricula);
    }
    public int mostrarNumeroInfracciones(String dni){
        return this.daoUsuarios.mostrarNumeroInfracciones(dni);
    }
    
    public String[] statsTmedioAparcar() {
        return daoPlazas.statsTmedioAparcar();
    }

    public List<HashMap<String, Object>> statsVecesUsuario() {
        return daoUsuarios.statsVecesUsuario();
    }

    public HashMap<String, List<HashMap<String, Object>>> statsPlazas() {
        return daoPlazas.statsPlazas();
    }
    /**
     * 
     * @return lista cos nomes dos usuarios cuxo veto foi quitado a causa de que 
     * pasaron 14 dias dende o veto ata o dia en que se abriu a aplicacion
     */
    public ArrayList<String> Actualizar(){
        ArrayList<String> usuariosSalvados = new ArrayList<>();//será a lista de nombres dos usuario aos cales se lles quitou o veto
        ArrayList<Usuario> usuarios = this.daoUsuarios.buscarUsuariosConVeto();
        
        LocalDate ahora = LocalDate.now();
        for(Usuario u : usuarios ){
            if(ChronoUnit.DAYS.between( u.getFechaVeto(),ahora)>14){
                this.daoUsuarios.quitarVeto(u.getDni());
                usuariosSalvados.add(u.getNombre());
            }
        }
        return usuariosSalvados;
    }
            
}
