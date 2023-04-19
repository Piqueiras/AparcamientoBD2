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
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOPlazas extends AbstractDAO {

    private FachadaAplicacion fa;

    public DAOPlazas(Connection conexion, aplication.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
        this.fa = fa;
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

            if (codigoPlaza != -1) {
                query += " AND  p.codigo=? ";
            }
            if (!tipoPlaza.isEmpty()) {
                query += " AND p.tipo=?";
            }

            /*Si ocupadas==false, voy a ver que no esten reservadas. Esto es, voy a acceder a la relacion PlazasReservar y ver que no existe esa plaza en una tupla con fechaFin>FechaActual*/
            if (!ocupadas) {
                query += " and not EXISTS(select* from Reservar r where r.codigoplazareserva=p.codigo and p.idAparcamiento=r.idAparcamiento and r.fechafin > NOW())";

            }
            statement = connection.prepareStatement(query);

            // Establecer los parámetros de la consulta
            statement.setString(1, "%" + codigoAparcamiento + "%");
            if (codigoPlaza != -1) {
                statement.setObject(2, codigoPlaza, Types.INTEGER);
                if (!tipoPlaza.isEmpty()) {
                    statement.setString(3, tipoPlaza);
                }
            } else if (!tipoPlaza.isEmpty()) {
                statement.setString(2, tipoPlaza);
            }

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

    public boolean hacerReserva(String matricula, int plaza, String aparcamiento, java.util.Date horaInicio, java.util.Date horaFin) {
        String query;
        boolean exito = true;
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

            if (codigoPlaza != -1) {
                query += " AND  p.codigo=? ";
            }
            if (!tipoPlaza.isEmpty()) {
                query += " AND p.tipo=?";
            }

            /*Si ocupadas==false, voy a ver que no esten ocupadas. Esto es, voy a acceder a la relacion PlazasAparcar y ver que no existe esa plaza en una tupla con fechaFin>FechaActual o fechaFin=NULL*/
            if (!ocupadas) {
                query += " and not EXISTS(select* from Aparcar r where r.codigoplaza=p.codigo and p.idAparcamiento=r.idAparcamiento and (r.fechasalida is NULL or r.fechasalida> NOW()))";

            }
            statement = connection.prepareStatement(query);

            // Establecer los parámetros de la consulta
            statement.setString(1, "%" + codigoAparcamiento + "%");
            if (codigoPlaza != -1) {
                statement.setObject(2, codigoPlaza, Types.INTEGER);
                if (!tipoPlaza.isEmpty()) {
                    statement.setString(3, tipoPlaza);
                }
            } else if (!tipoPlaza.isEmpty()) {
                statement.setString(2, tipoPlaza);
            }

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
        return plazas;
    }

    public boolean Aparcar(String matricula, int plaza, String aparcamiento) {
        String query;
        boolean exito = true;
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
        boolean exito = true;
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
        boolean exito = true;
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
        boolean exito = true;
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
        boolean exito = true;
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

    public String[] statsTmedioAparcar() {
        String query;

        //out[0] -> tmedio usc users
        //out[1] -> tmedio non usc users
        String[] out = new String[2];

        try {
            query
                    = "SELECT rol, SUM(usuariosClasificados.tiempototaparcado)/SUM(usuariosClasificados.vecesaparcado) AS tiempomedioaparcado "
                    + "FROM(SELECT 0 AS rol , SUM( a.fechasalida - a.fechaentrada ) AS tiempototaparcado, COUNT( a.fechasalida ) AS vecesaparcado "
                    + "FROM usuarios u, aparcar a WHERE (SELECT dni FROM vehiculos WHERE matricula = a.matriculavehiculo ) = u.dni AND u.fechaingresousc IS NOT NULL GROUP BY u.dni "
                    + "UNION(SELECT 1 AS rol, SUM( a2.fechasalida - a2.fechaentrada ) AS tiempototaparcado , COUNT( a2.fechasalida ) AS vecesaparcado "
                    + "FROM usuarios u2 , aparcar a2 WHERE (SELECT dni FROM vehiculos WHERE matricula = a2.matriculavehiculo ) = u2.dni AND u2.fechaingresousc IS NULL GROUP BY u2.dni ) ) "
                    + "AS usuariosClasificados GROUP BY usuariosClasificados.rol ;";

            Connection connection = this.getConexion();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                //Coger data
                int rol = rs.getInt("rol");

                //Obtiene el resultado de tiempo en formato crudo
                String timeRaw = rs.getString("tiempomedioaparcado").replace(" days", "d");
                String time = null;

                //En caso de que en el resultado aparezca un '.' (que de el resultado con ms), se quita el punto y todo lo que esté a posteriori
                int index = timeRaw.indexOf(".");
                if (index < 0) {
                    time = timeRaw;
                } else {
                    time = timeRaw.substring(0, index);
                }

                //Asginar valor el array según el rol
                out[rol] = time;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public List<HashMap<String, Object>> statsVecesUsuario() {
        String query;
        List out = new ArrayList();

        try {
            query
                    = "SELECT TvecesAparcadovecesReservado.nombre, TvecesAparcadovecesReservado.dni,  "
                    + "SUM(TvecesAparcadovecesReservado.vecesaparcad) AS vecesaparcado, "
                    + "SUM(TvecesAparcadovecesReservado.vecesreservad) AS vecesreservado "
                    + "FROM ( "
                    + "    SELECT u.dni, u.nombre, COUNT(*) AS vecesaparcad, 0 AS vecesreservad "
                    + "    FROM usuarios u, aparcar a "
                    + "    WHERE (SELECT dni FROM vehiculos WHERE matricula = a.matriculavehiculo) = u.dni "
                    + "    GROUP BY u.dni, u.nombre "
                    + "    UNION ALL "
                    + "    SELECT u2.dni, u2.nombre, 0 AS vecesaparcad, COUNT(*) AS vecesreservad "
                    + "    FROM usuarios u2, reservar r2 "
                    + "    WHERE (SELECT dni FROM vehiculos WHERE matricula = r2.matriculavehiculo) = u2.dni "
                    + "    GROUP BY u2.dni, u2.nombre "
                    + ") AS TvecesAparcadovecesReservado "
                    + "GROUP BY TvecesAparcadovecesReservado.nombre, TvecesAparcadovecesReservado.dni "
                    + "ORDER BY vecesaparcado DESC, vecesreservado DESC";

            // Crea una conexión a la base de datos
            Connection connection = this.getConexion();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //Asginar un HashMap por fila y meter todas las filas en la lista de out
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                Integer vecesAparcado = rs.getInt("vecesaparcado");
                Integer vecesReservado = rs.getInt("vecesreservado");

                HashMap<String, Object> datos = new HashMap<>();
                datos.put("nombre", nombre);
                datos.put("dni", dni);
                datos.put("vecesAparcado", vecesAparcado);
                datos.put("vecesReservado", vecesReservado);

                out.add(datos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public HashMap<String, List<HashMap<String, Object>>> statsPlazas() {
        String query;
        HashMap<String, List<HashMap<String, Object>>> datos = new HashMap<>();

        try {
            query = "SELECT 'MásAparcadas' AS categoria, p1.codigo AS plaza, p1.tipo AS tipo, COUNT(1) AS veces\n"
                    + "FROM plazasaparcar p1, aparcar a1\n"
                    + "WHERE p1.codigo = a1.codigoplaza\n"
                    + "GROUP BY p1.codigo, p1.tipo\n"
                    + "HAVING COUNT(1) >= ALL (\n"
                    + "SELECT COUNT(1)\n"
                    + "FROM plazasaparcar p11, aparcar a11\n"
                    + "WHERE p11.codigo = a11.codigoplaza\n"
                    + "GROUP BY p11.codigo, p11.tipo\n"
                    + ")\n"
                    + "UNION (\n"
                    + "SELECT 'MenosAparcadas' AS categoria, p2.codigo AS plaza, p2.tipo AS tipo, COUNT(1) AS veces\n"
                    + "FROM plazasaparcar p2, aparcar a2\n"
                    + "WHERE p2.codigo = a2.codigoplaza\n"
                    + "GROUP BY p2.codigo, p2.tipo\n"
                    + "HAVING COUNT(1) <= ALL (\n"
                    + "SELECT COUNT(1)\n"
                    + "FROM plazasaparcar p21, aparcar a21\n"
                    + "WHERE p21.codigo = a21.codigoplaza\n"
                    + "GROUP BY p21.codigo, p21.tipo\n"
                    + ")\n"
                    + ")\n"
                    + "UNION (\n"
                    + "SELECT 'MásReservadas' AS categoria, pr1.codigo AS plaza, pr1.tipo AS tipo, COUNT(1) AS veces\n"
                    + "FROM plazasreserva pr1, reservar r1\n"
                    + "WHERE pr1.codigo = r1.codigoplazareserva\n"
                    + "GROUP BY pr1.codigo, pr1.tipo\n"
                    + "HAVING COUNT(1) >= ALL (\n"
                    + "SELECT COUNT(1)\n"
                    + "FROM plazasreserva pr11, reservar r11\n"
                    + "WHERE pr11.codigo = r11.codigoplazareserva \n"
                    + "GROUP BY pr11.codigo, pr11.tipo\n"
                    + ")\n"
                    + ")\n"
                    + "UNION (\n"
                    + "SELECT 'MenosReservadas' AS categoria, pr2.codigo AS plaza, pr2.tipo AS tipo, COUNT(1) AS veces\n"
                    + "FROM plazasreserva pr2, reservar r2\n"
                    + "WHERE pr2.codigo = r2.codigoplazareserva \n"
                    + "GROUP BY pr2.codigo, pr2.tipo\n"
                    + "HAVING COUNT(1) <= ALL (\n"
                    + "SELECT COUNT(1)\n"
                    + "FROM plazasreserva pr21, reservar r21\n"
                    + "WHERE pr21.codigo = r21.codigoplazareserva \n"
                    + "GROUP BY pr21.codigo, pr21.tipo\n"
                    + ")\n"
                    + ")\n"
                    + "ORDER BY categoria;";

            // Crea una conexión a la base de datos 
            Connection connection = this.getConexion();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //En el HashMap ppal cada categoría da a la lista de las filas correspondiente
            while (rs.next()) {
                String categoria = rs.getString("categoria");
                HashMap<String, Object> fila = new HashMap<>();
                fila.put("plaza", rs.getString("plaza"));
                fila.put("tipo", rs.getString("tipo"));
                fila.put("veces", rs.getInt("veces"));

                if (datos.containsKey(categoria)) {
                    datos.get(categoria).add(fila);
                } else {
                    List<HashMap<String, Object>> lista = new ArrayList<>();
                    lista.add(fila);
                    datos.put(categoria, lista);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

}
