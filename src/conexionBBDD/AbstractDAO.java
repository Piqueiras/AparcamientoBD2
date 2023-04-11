/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexionBBDD;

/**
 *
 * @author basesdatos
 */
public abstract class AbstractDAO {
   private aplication.FachadaAplicacion fa;
   private java.sql.Connection conexion;

   
    protected java.sql.Connection getConexion(){
        return this.conexion;
    }
    
    protected void setConexion(java.sql.Connection conexion){
        this.conexion=conexion;
    }
   
   protected void setFachadaAplicacion(aplication.FachadaAplicacion fa){
       this.fa=fa;
   }
   
   protected aplication.FachadaAplicacion getFachadaAplicacion(){
       return fa;
   }
   
   
}
