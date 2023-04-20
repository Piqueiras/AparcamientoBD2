/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import aplication.*;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
          vp.setLocationRelativeTo(null);
     ImageIcon backgroundIcon = new ImageIcon("imagenfondo(1).png");
     
     JLabel backgroundLabel = new JLabel(backgroundIcon);
     backgroundLabel.setBounds(0,0,vp.getWidth(), vp.getHeight());
     
     vp.getContentPane().add(backgroundLabel, BorderLayout.CENTER);
     
   }
    
    public void iniciaVista(){
      VAutentificacion va;
    
      va = new VAutentificacion(vp, true, fa);
      //vp.setVisible(true);
      vp.setVisible(false);//cambieino para que non se vira xa ao principio si non eres administrador
      va.setLocationRelativeTo(null);
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
        vpagos.setLocationRelativeTo(null);
        vpagos.setVisible(true);
    }
    
    public void registrarUsuario(Usuario usuario){
        
    }
}
