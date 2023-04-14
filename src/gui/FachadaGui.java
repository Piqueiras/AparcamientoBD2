/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import aplication.*;

/**
 *
 * @author alumno
 */
public class FachadaGui {
    aplication.FachadaAplicacion fa;
    VPrincipal vp;
    
   public FachadaGui(aplication.FachadaAplicacion fa){
     this.fa=fa;
     this.vp = new VPrincipal(fa);
   }
    
    public void iniciaVista(){
      VAutentificacion va;
    
      va = new VAutentificacion(vp, true, fa);
      vp.setVisible(true);
      va.setVisible(true);
    }
    
    public void muestraExcepcion(String txtExcepcion){
       VAviso va;
       
       va = new VAviso(vp, true, txtExcepcion);
       va.setVisible(true);
    }
    
    public void historialPagos(){
        VPagos vpagos;
        
        vpagos=new VPagos(vp, true, fa);
        vpagos.setVisible(true);
    }
    
    public void registrarUsuario(Usuario usuario){
        
    }
}
