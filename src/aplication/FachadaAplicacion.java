/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplication;
import conexionBBDD.FachadaBaseDatos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.List;

/**
 *
 * @author basesdatos
 */
public class FachadaAplicacion {
    gui.FachadaGui fgui;
    conexionBBDD.FachadaBaseDatos fbd;
    Usuario u;
    ArrayList<String> usuariosSalvados = new ArrayList<>();
    
    public FachadaAplicacion() {
      fgui=new gui.FachadaGui(this);
      fbd=new conexionBBDD.FachadaBaseDatos(this);
    }

    public FachadaBaseDatos getFbd() {
        return fbd;
    }

    public ArrayList<String> getUsuariosSalvados() {
        return usuariosSalvados;
    }
    
    public Usuario getUsuario () {
        return this.u;
    }
    
    public void setUsuario (Usuario u) {
        this.u = u;
    }
    
    public static void main(String args[]) {
        FachadaAplicacion fa;

        fa= new FachadaAplicacion();
        fa.usuariosSalvados = fa.Actualizar(); //Actualiza os vetos
        
        fa.iniciaInterfazUsuario();
    }

    public void iniciaInterfazUsuario(){
        fgui.iniciaVista();
    }

    public void muestraExcepcion(String e){
        fgui.muestraExcepcion(e);
    }
    
   /**
     * Este metodo se ejecuta al principio del programa. A traves de la llamada de varios metodos en otras clases, se acabara llegando
     * al metodo encargado de comprobar que el dni introducido es de un administrador.
     * @param dni - dni a verificar
     * @return Usuario - Si el usuario esta en la base de datos y es un administrador, se devuelve una instancia de Usuario con sus datos.
     */
    public Usuario comprobarAutentificacion(String dni){
        Usuario u = null;

        u=fbd.validarUsuario(dni);
        return u;
    }
    
    /**
     * Este metodo es llamado cuando se presiona el boton del historial de pagos. Llama al metodo de la fachada GUI que inicializa la ventana de los pagos.
     */
    public void historialPagos() {
        fgui.historialPagos();
    }
    
    /** 
     * Este metodo llegara al metodo que obtiene todas las tuplas de la tabla Aparcar de la base de datos que cumplan las condiciones especificadas.
     * Pasa por FachadaBaseDatos para llegar a DAOUsuarios.
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
    public List<Aparcar> historialAparcar(String dni, String mat, String pza, String ap, String cMax, String cMin, String dMax, String dMin, String fMax, String fMin) {
        return fbd.historialAparcar(dni, mat, pza, ap, cMax, cMin, dMax, dMin, fMax, fMin);
    }
    
    /**
     * Este metodo llegara al metodo que obtiene todas las tuplas de la tabla Reservar de la base de datos que cumplan las condiciones especificadas.
     * Pasa por FachadaBaseDatos para llegar a DAOUsuarios.
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
    public List<Reservar> historialReservar(String dni, String mat, String pza, String ap, String cMax, String cMin, String dMax, String dMin, String fMax, String fMin) {
        return fbd.historialReservar(dni, mat, pza, ap, cMax, cMin, dMax, dMin, fMax, fMin);
    }
    
    /**
     * Este metodo recibe la clave primaria de una tupla de Aparcar y llama a un metodo que actualiza en la base de datos la tupla,
     * asignandole al campo 'fechasalida' el momento actual. Es llamado por VPagos.
     * @param mat - Matricula del vehiculo
     * @param pza - Plaza donde esta aparcado el vehiculo
     * @param ap - Aparcamiento donde esta aparcado el vehiculo
     * @param fi - Fecha de entrada del vehiculo
     */
    public void retirarVehiculo(String mat, String pza, String ap, String fi) {
        fbd.retirarVehiculo(mat, pza, ap, fi);
    }
    
        public List<PlazaReserva> obtenerPlazasReserva(String codigoAparcamiento, Integer  codigoPlaza, String tipoPlaza, boolean ocupadas){
        return fbd.obtenerPlazasReserva(codigoAparcamiento,codigoPlaza,tipoPlaza,ocupadas);
    }
    
    public List<PlazaAparcar> obtenerPlazasAparcar(String codigoAparcamiento, Integer  codigoPlaza, String tipoPlaza, boolean ocupadas){
        return fbd.obtenerPlazasAparcar(codigoAparcamiento,codigoPlaza,tipoPlaza,ocupadas);
    }

    public List<Vehiculo> obtenerVehiculosReserva(String dni, String tipoletra) {
        return fbd.obtenerVehiculos(dni, tipoletra);
    }

    public List<Vehiculo> obtenerVehiculosReserva(String tipoletra) {
        return fbd.obtenerVehiculos(tipoletra);
    }

    public List<Vehiculo> obtenerVehiculosNoAparcados(String dni, String tipoletra){
        return fbd.obtenerVehiculosNoAparcados(dni, tipoletra);
    }
    
    public List<Vehiculo> obtenerVehiculosNoAparcados(String tipoletra){
        return fbd.obtenerVehiculosNoAparcados(tipoletra);
    }

    public boolean hacerReserva(String matricula, int plaza, String aparcamiento, Date horaInicio, Date horaFin){
        return fbd.hacerReserva(matricula,plaza,aparcamiento,horaInicio,horaFin);
    }
    
    public String obtenerRol(String dni){
        return fbd.obtenerRol(dni);
    }

    public boolean Aparcar(String matricula, int plaza, String aparcamiento) {
        return fbd.Aparcar(matricula,plaza,aparcamiento);
    }
    
    public boolean anadirPlaza(String codigoAparcamiento, int codigoPlaza, String tipoPlaza) {
        return fbd.anadirPlaza(codigoAparcamiento,codigoPlaza,tipoPlaza);
    }
    
    public boolean anadirPlazaReserva(String codigoAparcamiento, int codigoPlaza, String tipoPlaza) {
        return fbd.anadirPlazaReserva(codigoAparcamiento, codigoPlaza, tipoPlaza);
    }
    
    public boolean eliminarPlazaAparcar(int codigoAparcamiento) throws SQLException{
        try{
            return fbd.eliminarPlazaAparcar(codigoAparcamiento);
        }catch(SQLException e){
            throw e;
        }
    }
    
    public boolean eliminarPlazaReserva(int codigoAparcamiento) throws SQLException{
        try{
            return fbd.eliminarPlazaReserva(codigoAparcamiento);
        }catch(SQLException e){
            throw e;
        }
    }
        
    public boolean registrarUsuario(Usuario usuario){
        return fbd.registrarUsuario(usuario);
    }
    
    public boolean registrarVehiculo(String dni, Vehiculo vehiculo){
        return fbd.registrarVehiculo(dni, vehiculo);
    }
    
    /**
     * @param matricula
     * @return o usuario propietario do vehiculo con matricula [matricula]
     */
    public Usuario obtenerUsuario(String matricula){
        return fbd.obtenerUsuario(matricula);
    }
     /**
     * @param dni
     * @return numero de infracciones desde que se quitou, en caso de que o houbese, o ultimo veto
     */
    public int registrarInfraccion(String dni){
        return fbd.registrarInfraccion(dni);
    }
    public int mostrarNumeroInfracciones(String dni){
        return fbd.mostrarNumeroInfracciones(dni);
    }
    
    public String[] statsTmedioAparcar() {
        return fbd.statsTmedioAparcar();
    }

    public List<HashMap<String, Object>> statsVecesUsuario() {
        return fbd.statsVecesUsuario();
    }

    public HashMap<String, List<HashMap<String, Object>>> statsPlazas() {
        return fbd.statsPlazas();
    }
    /**
     * Actualiza os vetos:
     * Recorrrendoos usuarios con vetos, si pasan máis de catorce días entre o veto do usuario
     * e o dia que se abre a aplicacion, entonces elimínase o veto.
     */
    public ArrayList<String> Actualizar(){
        return this.fbd.Actualizar();
    }
            
}
