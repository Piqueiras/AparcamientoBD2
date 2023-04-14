/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionBBDD;

import aplication.PlazaReserva;
import aplication.Aparcamiento;
import aplication.FachadaAplicacion;
import aplication.PlazaAparcar;
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

public  boolean hacerReserva(String matricula, int plaza,String aparcamiento, java.util.Date horaInicio, java.util.Date horaFin) {
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
        

        public List<PlazaAparcar> obtenerPlazasAparcar(String codigoAparcamiento, Integer codigoPlaza, String tipoPlaza, boolean ocupadas) {
 String query;
    PreparedStatement statement = null;
    List<PlazaAparcar> plazas = new ArrayList<>();
    // Crea una conexión a la base de datos
    Connection connection = this.getConexion();
    try {
        // Crear un objeto PreparedStatement con la consulta
        query = "SELECT codigo, tipo, idAparcamiento FROM PlazasAparcar p WHERE p.idAparcamiento like ? ";

        if(codigoPlaza!=-1){
            query+=" AND  p.codigo=? ";}
        if(!tipoPlaza.isEmpty()) query+= " AND p.tipo=?";

        /*Si ocupadas==false, voy a ver que no esten ocupadas. Esto es, voy a acceder a la relacion PlazasAparcar y ver que no existe esa plaza en una tupla con fechaFin>FechaActual o fechaFin=NULL*/
        if(!ocupadas){ query+=" and not EXISTS(select* from Aparcar r where r.codigoplaza=p.codigo and p.idAparcamiento=r.idAparcamiento and (r.fechasalida is NULL or r.fechasalida> NOW()))";

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
            PlazaAparcar plaza = new PlazaAparcar(codigo, tipo, new Aparcamiento(idAparcamiento));

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
    return plazas;        }

    public boolean Aparcar(String matricula, int plaza, String aparcamiento){
         String query;
        boolean exito=true;
        PreparedStatement statement = null;
        // Crea una conexión a la base de datos
        Connection connection = this.getConexion();
        try {
            query = "INSERT INTO aparcar (matriculaVehiculo, codigoPlaza, idAparcamiento, fechaentrada) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            
            // Asignar los valores a los parámetros de la sentencia SQL
            statement.setString(1, matricula);
            statement.setInt(2, plaza);
            statement.setString(3, aparcamiento);
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            // Ejecutar la sentencia SQL INSERT
            int filasAfectadas = statement.executeUpdate();
            
            // Retornar true si se insertó la reserva correctamente, false en caso contrario
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean anadirPlaza(String codigoAparcamiento, int codigoPlaza, String tipoPlaza) {
        String query;
        boolean exito=true;
        PreparedStatement statement = null;
        // Crea una conexión a la base de datos
        Connection connection = this.getConexion();
        try {
            query = "INSERT INTO PlazasAparcar (codigo, tipo, idAparcamiento) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            
            // Asignar los valores a los parámetros de la sentencia SQL
            statement.setInt(1, codigoPlaza);
            
            switch (tipoPlaza) {
                case "Coche":
                    statement.setString(2, "C");
                    break;
                case "Moto":
                    statement.setString(2, "M");
                    break;
                case "Vehiculo Grande":
                    statement.setString(2, "G");
                    break;
                default:
                        break;
            }

            statement.setString(3, codigoAparcamiento);
            
            // Ejecutar la sentencia SQL INSERT
            int filasAfectadas = statement.executeUpdate();
            
            // Retornar true si se insertó la reserva correctamente, false en caso contrario
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

public boolean anadirPlazaReserva(String codigoAparcamiento, int codigoPlaza, String tipoPlaza) {
        String query;
        boolean exito=true;
        PreparedStatement statement = null;
        // Crea una conexión a la base de datos
        Connection connection = this.getConexion();
        try {
            query = "INSERT INTO PlazasReserva (codigo, tipo, idAparcamiento) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            
            // Asignar los valores a los parámetros de la sentencia SQL
            statement.setInt(1, codigoPlaza);
            
            switch (tipoPlaza) {
                case "Coche":
                    statement.setString(2, "C");
                    break;
                case "Moto":
                    statement.setString(2, "M");
                    break;
                case "Vehiculo Grande":
                    statement.setString(2, "G");
                    break;
                default:
                        break;
            }

            statement.setString(3, codigoAparcamiento);
            
            // Ejecutar la sentencia SQL INSERT
            int filasAfectadas = statement.executeUpdate();
            
            // Retornar true si se insertó la reserva correctamente, false en caso contrario
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPlazaAparcar(int codigo) {
        String query;
        boolean exito=true;
        PreparedStatement statement = null;
        // Crea una conexión a la base de datos
        Connection connection = this.getConexion();
        try {
            
            query = "DELETE FROM PlazasAparcar WHERE codigo = ?";
            statement = connection.prepareStatement(query);
            
            // Asignar los valores a los parámetros de la sentencia SQL
            statement.setObject(1, codigo, Types.INTEGER);
            
            // Ejecutar la sentencia SQL INSERT
            int filasAfectadas = statement.executeUpdate();
            
            // Retornar true si se insertó la reserva correctamente, false en caso contrario
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarPlazaReserva(int codigo) {
        String query;
        boolean exito=true;
        PreparedStatement statement = null;
        // Crea una conexión a la base de datos
        Connection connection = this.getConexion();
        try {
            
            query = "DELETE FROM PlazasReserva WHERE codigo = ?";
            statement = connection.prepareStatement(query);
            
            // Asignar los valores a los parámetros de la sentencia SQL
            statement.setObject(1, codigo, Types.INTEGER);
            
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
