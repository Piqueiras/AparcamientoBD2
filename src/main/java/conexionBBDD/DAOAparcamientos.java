/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionBBDD;

import java.sql.*;

/**
 *
 * @author alumnogreibd
 */
public class DAOAparcamientos extends AbstractDAO {
    
    public DAOAparcamientos (Connection conexion, aplication.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
     
}