/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionBBDD;

import aplication.PlazaReserva;
import aplication.Aparcamiento;
import aplication.TipoPlaza;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class DAOPlazas extends AbstractDAO {
    
    public DAOPlazas (Connection conexion, aplication.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    public List<PlazaReserva> obtenerPlazasReserva(String codigoAparcamiento, Integer codigoPlaza, String tipoPlaza, boolean ocupadas) {
    String query;
    PreparedStatement statement = null;
    List<PlazaReserva> plazas = new ArrayList<>();
    // Crea una conexión a la base de datos
    Connection connection = this.getConexion();
    try {
        // Crear un objeto PreparedStatement con la consulta
        query = "SELECT codigo, tipo, idAparcamiento FROM PlazasReserva p WHERE p.idAparcamiento like ? ";

        if(codigoPlaza!=-1){
            query+=" AND  p.codigo=? ";}
        if(!tipoPlaza.isEmpty()) query+= " AND p.tipo=?";

        /*Si ocupadas==false, voy a ver que no esten reservadas. Esto es, voy a acceder a la relacion PlazasReservar y ver que no existe esa plaza en una tupla con fechaFin>FechaActual*/
        if(!ocupadas){ query+=" and not EXISTS(select* from Reservar r where r.codigoplazareserva=p.codigo and p.idAparcamiento=r.idAparcamiento)";

        }
        statement = connection.prepareStatement(query);
        

        // Establecer los parámetros de la consulta
        statement.setString(1, "%"+codigoAparcamiento+"%");
        if(codigoPlaza!=-1){
            statement.setObject(2, codigoPlaza, Types.INTEGER);
            if(!tipoPlaza.isEmpty())statement.setString(3, tipoPlaza);
        }else  if(!tipoPlaza.isEmpty())statement.setString(2, tipoPlaza);


        // Ejecutar la consulta y obtener un conjunto de resultados
        ResultSet resultSet = statement.executeQuery();

        // Recorrer los resultados y crear un objeto Plaza por cada fila
        while (resultSet.next()) {
            // Obtener los valores de cada columna en la fila actual
            int codigo = resultSet.getInt("codigo");
            TipoPlaza tipo = TipoPlaza.valueOf(resultSet.getString("tipo"));
            String idAparcamiento = resultSet.getString("idAparcamiento");

            // Crear un objeto Plaza a partir de los valores obtenidos
            PlazaReserva plaza = new PlazaReserva(codigo, tipo, new Aparcamiento(idAparcamiento));

            // Añadir el objeto Plaza a la lista
            plazas.add(plaza);
        }

        // Cerrar los recursos (en orden inverso al que fueron abiertos)
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
    }
    // Devolver la lista de objetos Plaza
    return plazas;
}

}
