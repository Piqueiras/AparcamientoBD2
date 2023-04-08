/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import aplication.Clase;

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
}
