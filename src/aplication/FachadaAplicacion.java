/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplication;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    
    public FachadaAplicacion() {
      fgui=new gui.FachadaGui(this);
      fbd=new conexionBBDD.FachadaBaseDatos(this);
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
        fa.iniciaInterfazUsuario();
    }

    public void iniciaInterfazUsuario(){
        fgui.iniciaVista();
    }

    public void muestraExcepcion(String e){
        fgui.muestraExcepcion(e);
    }
    
    public Usuario comprobarAutentificacion(String dni){
        Usuario u = null;

        u=fbd.validarUsuario(dni);
        return u;
    }
    
    public void historialPagos() {
        fgui.historialPagos();
    }
    
    public List<Aparcar> historialAparcar(String dni, String mat, String pza, String ap, String cMax, String cMin, String dMax, String dMin, String fMax, String fMin) {
        return fbd.historialAparcar(dni, mat, pza, ap, cMax, cMin, dMax, dMin, fMax, fMin);
    }
    
    public List<Reservar> historialReservar(String dni, String mat, String pza, String ap, String cMax, String cMin, String dMax, String dMin, String fMax, String fMin) {
        return fbd.historialReservar(dni, mat, pza, ap, cMax, cMin, dMax, dMin, fMax, fMin);
    }
    
        public List<PlazaReserva> obtenerPlazasReserva(String codigoAparcamiento, Integer  codigoPlaza, String tipoPlaza, boolean ocupadas){
        return fbd.obtenerPlazasReserva(codigoAparcamiento,codigoPlaza,tipoPlaza,ocupadas);
    }
    
    public List<PlazaAparcar> obtenerPlazasAparcar(String codigoAparcamiento, Integer  codigoPlaza, String tipoPlaza, boolean ocupadas){
        return fbd.obtenerPlazasAparcar(codigoAparcamiento,codigoPlaza,tipoPlaza,ocupadas);
    }

    public List<Vehiculo> obtenerVehiculosReserva(String dni) {
        return fbd.obtenerVehiculos(dni);
    }
        
    public List<Vehiculo> obtenerVehiculosReserva() {
        return fbd.obtenerVehiculos();
    }
    
    public List<Vehiculo> obtenerVehiculosNoAparcados(String dni){
        return fbd.obtenerVehiculosNoAparcados(dni);
    }
    
    public List<Vehiculo> obtenerVehiculosNoAparcados(){
        return fbd.obtenerVehiculosNoAparcados();
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
    
    public boolean eliminarPlazaAparcar(int codigoAparcamiento){
        return fbd.eliminarPlazaAparcar(codigoAparcamiento);
    }
    
    public boolean eliminarPlazaReserva(int codigoAparcamiento){
        return fbd.eliminarPlazaReserva(codigoAparcamiento);
    }
        
    public boolean registrarUsuario(Usuario usuario){
        return fbd.registrarUsuario(usuario);
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
            
}
