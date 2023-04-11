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
        fgui.historialPagos(this.u.getDni());
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
}
