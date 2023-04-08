/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplication;

/**
 *
 * @author basesdatos
 */
public class FachadaAplicacion {
    gui.FachadaGui fgui;
    conexionBBDD.FachadaBaseDatos fbd;
    //GesionLibros cl;
    //GestionUsuarios cu;
    
    public FachadaAplicacion(){
      fgui=new gui.FachadaGui(this);
      fbd= new conexionBBDD.FachadaBaseDatos(this);
      cl= new GesionLibros(fgui, fbd);
      cu= new GestionUsuarios(fgui, fbd);
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

   public Boolean comprobarAutentificacion(String idUsuario, String clave){
     return cu.comprobarAutentificacion(idUsuario, clave);
   }
}
