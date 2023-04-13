/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionBBDD;

import aplication.PlazaReserva;
import aplication.Aparcamiento;
import aplication.FachadaAplicacion;
import aplication.TipoPlaza;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOPlazas extends AbstractDAO {
    private FachadaAplicacion fa;
    public DAOPlazas (Connection conexion, aplication.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
        this.fa=fa;
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
        if(!ocupadas){ query+=" and not EXISTS(select* from Reservar r where r.codigoplazareserva=p.codigo and p.idAparcamiento=r.idAparcamiento and r.fechafin > NOW())";

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

    boolean hacerReserva(String matricula, int plaza,String aparcamiento, java.util.Date horaInicio, java.util.Date horaFin) {
        String query;
        boolean exito=true;
        PreparedStatement statement = null;
        // Crea una conexión a la base de datos
        Connection connection = this.getConexion();
        try {
            query = "INSERT INTO reservar (matriculaVehiculo, codigoPlazaReserva, idAparcamiento, fechaInicio, fechaFin) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            
            // Asignar los valores a los parámetros de la sentencia SQL
            statement.setString(1, matricula);
            statement.setInt(2, plaza);
            statement.setString(3, aparcamiento);
            statement.setTimestamp(4, new Timestamp(horaInicio.getTime()));
            statement.setTimestamp(5, new Timestamp(horaFin.getTime()));

            // Ejecutar la sentencia SQL INSERT
            int filasAfectadas = statement.executeUpdate();
            
            // Retornar true si se insertó la reserva correctamente, false en caso contrario
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        

}
