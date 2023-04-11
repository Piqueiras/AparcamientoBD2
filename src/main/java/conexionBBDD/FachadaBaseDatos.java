/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexionBBDD;

//import aplication.<nombreClase>;
import aplication.Aparcar;
import aplication.Reservar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import aplication.Usuario;
import java.util.List;
import conexionBBDD.DAOUsuarios;

/**
 *
 * @author basesdatos
 */
public class FachadaBaseDatos {
    private aplication.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOUsuarios daoUsuarios;
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
}
