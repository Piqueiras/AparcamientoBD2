/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionBBDD;

import java.sql.*;
import aplication.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOUsuarios extends AbstractDAO {
    
    public DAOUsuarios (Connection conexion, aplication.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public Usuario validarUsuario(String dni){
        Usuario resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            //consulta completa
            stmUsuario=con.prepareStatement("select dni, nombre, telefono, correo, fechaIngresoUSC, rol, fechaVeto, numeroInfracciones "+
                                            "from Usuarios "+
                                            "where dni like ? ");
            
            stmUsuario.setString(1, dni);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
                {
                    resultado = new Usuario(rsUsuario.getString("dni"), rsUsuario.getString("nombre"),
                            rsUsuario.getString("telefono"), rsUsuario.getString("correo"),
                            aplication.RolUsuario.valueOf(rsUsuario.getString("rol")),
                            rsUsuario.getInt("numeroInfracciones"));
                    
                    if (rsUsuario.getObject("fechaIngresoUSC") != null) {
                        resultado.setFechaIngreso(rsUsuario.getDate("fechaIngresoUSC").toLocalDate());
                    }
                    if (rsUsuario.getObject("fechaVeto") != null) {
                        resultado.setFechaVeto(rsUsuario.getDate("fechaVeto").toLocalDate());
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            } finally {
                try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        return resultado;
    }
    
    public List<Aparcar> ConsultarHistorialAparcar(String dni, String mat, String pza, String ap, String cMax, String cMin, String dMax, String dMin, String fMax, String fMin) {
        java.util.List<Aparcar> resultado = new java.util.ArrayList<>();
        Vehiculo vehiculoActual;
        Usuario usuario;
        RolUsuario rol;
        Aparcar aparcarActual;
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;
        PreparedStatement stmVehiculo = null;
        ResultSet rsVehiculo;
        PreparedStatement stmAparcar=null;
        ResultSet rsAparcar;
        String partes[];
        String aux;
        int cAct;
        int cSat;

        con=this.getConexion();
        
        String consulta = "select dni, matricula "
                         + "from Vehiculos "
                         + "where dni like ";
        
        if (!dni.isEmpty()) {
            consulta = consulta + "\'" + dni + "\'";
        }
        else {
            consulta = consulta + "\'%\'";
        }
        
        try{
            stmVehiculo = con.prepareStatement(consulta);
            rsVehiculo = stmVehiculo.executeQuery();
            
            while (rsVehiculo.next()) {
                usuario = new Usuario(rsVehiculo.getString("dni"));
                vehiculoActual = new Vehiculo(rsVehiculo.getString("matricula"), usuario);
                
                consulta = "select rol from Usuarios where dni like \'" + usuario.getDni() + "\'";
                
                stmUsuario = con.prepareStatement(consulta);
                rsUsuario = stmUsuario.executeQuery();
                rsUsuario.next();
                rol = RolUsuario.valueOf(rsUsuario.getString("rol"));
                
                consulta = "select matriculaVehiculo, codigoPlaza, idAparcamiento, fechaentrada, fechasalida " +
                           "from aparcar "+
                           "where matriculaVehiculo like \'";
                
                if (mat.isEmpty()) {
                    consulta = consulta + vehiculoActual.getMatricula() + "\'";
                }
                else{
                    consulta = consulta + mat + "\'";
                }
                
                if (!pza.isEmpty()) {
                    consulta = consulta + " and codigoPlaza = " + pza + " ";
                }
                if (!ap.isEmpty()) {
                    consulta = consulta + " and idAparcamiento like \'" + ap + "\'";
                }
                if (!fMax.isEmpty()) {
                    consulta = consulta + " and fechaSalida <= \'" + fMax + "\'";
                }
                if (!fMin.isEmpty()) {
                    consulta = consulta + " and fechaEntrada >= \'" + fMin + "\'";
                }
                
                if (mat.isEmpty() || vehiculoActual.getMatricula().equals(mat)) {
                    try {
                        stmAparcar=con.prepareStatement(consulta);
                        rsAparcar=stmAparcar.executeQuery();

                        while (rsAparcar.next()) {
                            cAct = 0; //numero de condiciones de coste y duracion activas
                            cSat = 0; //numero de condiciones de coste y duracion que se cumplen
                            aparcarActual = new Aparcar(vehiculoActual, rsAparcar.getInt("codigoPlaza"),
                                                  rsAparcar.getString("idAparcamiento"),
                                                    rsAparcar.getTimestamp("fechaentrada").toLocalDateTime(), rol);
                            
                            if (rsAparcar.getObject("fechasalida") != null) {
                                aparcarActual.setFechaSalida(rsAparcar.getTimestamp("fechasalida").toLocalDateTime());
                            }
                            
                            //Para los parametros que no estan en la consulta porque son calculados y no estan en la tabla
                            if (!cMax.isEmpty()){
                                if (aparcarActual.getPrecio() <= Double.valueOf(cMax)){
                                    cSat += 1;
                                }
                                cAct += 1;
                            }
                            if (!cMin.isEmpty()){
                                if (aparcarActual.getPrecio() >= Double.valueOf(cMin)) {
                                    cSat += 1;
                                }
                                cAct += 1;
                            }
                            if (!dMax.isEmpty()){
                                aux = dMax.replaceAll(" ", "");
                                partes = aux.split("[dhms]");
                                if ((aparcarActual.getHoras() < Integer.valueOf(partes[0]) * 24) ||
                                    (aparcarActual.getHoras() == (Integer.valueOf(partes[0]) * 24) && aparcarActual.getHoras() < Integer.valueOf(partes[1])) ||
                                    (aparcarActual.getHoras() == (Integer.valueOf(partes[0]) * 24) && aparcarActual.getHoras() == Integer.valueOf(partes[1]) && aparcarActual.getMinutos() < Integer.valueOf(partes[2])) ||
                                    (aparcarActual.getHoras() == (Integer.valueOf(partes[0]) * 24) && aparcarActual.getHoras() == Integer.valueOf(partes[1]) && aparcarActual.getMinutos() == Integer.valueOf(partes[2]) && aparcarActual.getSegundos() <= Integer.valueOf(partes[3]))) {
                                    cSat += 1;
                                }
                                cAct += 1;
                            }
                            if (!dMin.isEmpty()){
                                aux = dMin.replaceAll(" ", "");
                                partes = aux.split("[dhms]");
                                if ((aparcarActual.getHoras() > Integer.valueOf(partes[0]) * 24) ||
                                    (aparcarActual.getHoras() == (Integer.valueOf(partes[0]) * 24) && aparcarActual.getHoras() > Integer.valueOf(partes[1])) ||
                                    (aparcarActual.getHoras() == (Integer.valueOf(partes[0]) * 24) && aparcarActual.getHoras() == Integer.valueOf(partes[1]) && aparcarActual.getMinutos() > Integer.valueOf(partes[2])) ||
                                    (aparcarActual.getHoras() == (Integer.valueOf(partes[0]) * 24) && aparcarActual.getHoras() == Integer.valueOf(partes[1]) && aparcarActual.getMinutos() == Integer.valueOf(partes[2]) && aparcarActual.getSegundos() >= Integer.valueOf(partes[3]))) {
                                    cSat += 1;
                                }
                                cAct += 1;
                            }
                            if(cSat == cAct) {
                                resultado.add(aparcarActual);
                            }
                        }
                    } catch (SQLException e){
                        System.out.println(e.getMessage());
                        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
                    } finally {
                        stmAparcar.close();
                    }
                }    
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
          try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return resultado;
    }
    
    public List<Reservar> ConsultarHistorialReservar(String dni, String mat, String pza, String ap, String cMax, String cMin, String dMax, String dMin, String fMax, String fMin) {
        java.util.List<Reservar> resultado = new java.util.ArrayList<>();
        Vehiculo vehiculoActual;
        Usuario usuario;
        RolUsuario rol;
        Reservar reservarActual;
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;
        PreparedStatement stmVehiculo = null;
        ResultSet rsVehiculo;
        PreparedStatement stmReservar=null;
        ResultSet rsReservar;
        String partes[];
        String aux;
        int cAct;
        int cSat;

        con=this.getConexion();
        
        String consulta = "select dni, matricula "
                         + "from Vehiculos "
                         + "where dni like ";
        
        if (!dni.isEmpty()) {
            consulta = consulta + "\'" + dni + "\'";
        }
        else {
            consulta = consulta + "\'%\'";
        }
        
        try{
            stmVehiculo = con.prepareStatement(consulta);
            rsVehiculo = stmVehiculo.executeQuery();
            
            while (rsVehiculo.next()) {
                usuario = new Usuario(rsVehiculo.getString("dni"));
                vehiculoActual = new Vehiculo(rsVehiculo.getString("matricula"), usuario);
                
                consulta = "select rol from Usuarios where dni like \'" + usuario.getDni() + "\'";
                
                stmUsuario = con.prepareStatement(consulta);
                rsUsuario = stmUsuario.executeQuery();
                rsUsuario.next();
                rol = RolUsuario.valueOf(rsUsuario.getString("rol"));
                
                consulta = "select matriculaVehiculo, codigoPlazaReserva, idAparcamiento, fechaInicio, fechaFin " +
                           "from reservar "+
                           "where matriculaVehiculo like \'";
                
                if (mat.isEmpty()) {
                    consulta = consulta + vehiculoActual.getMatricula() + "\'";
                }
                else{
                    consulta = consulta + mat + "\'";
                }
                
                if (!pza.isEmpty()) {
                    consulta = consulta + " and codigoPlazaReserva = " + pza + " ";
                }
                if (!ap.isEmpty()) {
                    consulta = consulta + " and idAparcamiento like \'" + ap + "\'";
                }
                if (!fMax.isEmpty()) {
                    consulta = consulta + " and fechaInicio <= \'" + fMax + "\'";
                }
                if (!fMin.isEmpty()) {
                    consulta = consulta + " and fechaFin >= \'" + fMin + "\'";
                }
                if (mat.isEmpty() || vehiculoActual.getMatricula().equals(mat)) {
                    try {
                        stmReservar=con.prepareStatement(consulta);
                        rsReservar=stmReservar.executeQuery();

                        while (rsReservar.next()) {
                            cAct = 0; //numero de condiciones de coste y duracion activas
                            cSat = 0; //numero de condiciones de coste y duracion que se cumplen
                            reservarActual = new Reservar(vehiculoActual, rsReservar.getInt("codigoPlazaReserva"),
                                                  rsReservar.getString("idAparcamiento"),
                                                    rsReservar.getTimestamp("fechaInicio").toLocalDateTime(),
                                                     rsReservar.getTimestamp("fechaFin").toLocalDateTime(), rol);
                            
                            if (rsReservar.getObject("fechaFin") != null) {
                                reservarActual.setFechaSalida(rsReservar.getTimestamp("fechaFin").toLocalDateTime());
                            }
                            
                            //Para los parametros que no estan en la consulta porque son calculados y no estan en la tabla
                            if (!cMax.isEmpty()){
                                if (reservarActual.getPrecio() <= Double.valueOf(cMax)){
                                    cSat += 1;
                                }
                                cAct += 1;
                            }
                            if (!cMin.isEmpty()){
                                if (reservarActual.getPrecio() >= Double.valueOf(cMin)) {
                                    cSat += 1;
                                }
                                cAct += 1;
                            }
                            if (!dMax.isEmpty()){
                                aux = dMax.replaceAll(" ", "");
                                partes = aux.split("[dhms]");
                                if ((reservarActual.getDias() < Integer.valueOf(partes[0])) ||
                                    (reservarActual.getDias() == Integer.valueOf(partes[0]) && reservarActual.getHoras() < Integer.valueOf(partes[1])) ||
                                    (reservarActual.getDias() == Integer.valueOf(partes[0]) && reservarActual.getHoras() == Integer.valueOf(partes[1]) && reservarActual.getMinutos() < Integer.valueOf(partes[2])) ||
                                    (reservarActual.getDias() == Integer.valueOf(partes[0]) && reservarActual.getHoras() == Integer.valueOf(partes[1]) && reservarActual.getMinutos() == Integer.valueOf(partes[2]) && reservarActual.getSegundos() <= Integer.valueOf(partes[3]))) {
                                    cSat += 1;
                                }
                                cAct += 1;
                            }
                            if (!dMin.isEmpty()){
                                aux = dMin.replaceAll(" ", "");
                                partes = aux.split("[dhms]");
                                if ((reservarActual.getDias() > Integer.valueOf(partes[0])) ||
                                    (reservarActual.getDias() == Integer.valueOf(partes[0]) && reservarActual.getHoras() > Integer.valueOf(partes[1])) ||
                                    (reservarActual.getDias() == Integer.valueOf(partes[0]) && reservarActual.getHoras() == Integer.valueOf(partes[1]) && reservarActual.getMinutos() > Integer.valueOf(partes[2])) ||
                                    (reservarActual.getDias() == Integer.valueOf(partes[0]) && reservarActual.getHoras() == Integer.valueOf(partes[1]) && reservarActual.getMinutos() == Integer.valueOf(partes[2]) && reservarActual.getSegundos() >= Integer.valueOf(partes[3]))) {
                                    cSat += 1;
                                }
                                cAct += 1;
                            }
                            if(cSat == cAct) {
                                resultado.add(reservarActual);
                            }
                        }
                    } catch (SQLException e){
                        System.out.println(e.getMessage());
                        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
                    } finally {
                        stmReservar.close();
                    }
                }    
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
          try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return resultado;
    }

   List<Vehiculo> obtenerVehiculos() {
        String query;
        PreparedStatement statement = null;
        List<Vehiculo> vehiculos = new ArrayList<>();
        // Crea una conexión a la base de datos
        Connection connection = this.getConexion();
        try {
            // Crear un objeto PreparedStatement con la consulta
            query = "SELECT * FROM Vehiculos v where v.dni not in (select dni from usuarios where fechaveto is not NULL)";
            statement = connection.prepareStatement(query);
            // Establecer los parámetros de la consulta

            // Ejecutar la consulta y obtener un conjunto de resultados
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los resultados y crear un objeto Plaza por cada fila
            while (resultSet.next()) {
                // Obtener los valores de cada columna en la fila actual
                String matricula = resultSet.getString("matricula");
                TipoPlaza tipo = TipoPlaza.valueOf(resultSet.getString("tipo"));
                String marca = resultSet.getString("marca");
                String modelo= resultSet.getString("modelo");
                Integer anho= resultSet.getInt("anhomatriculacion");
                String dni2=resultSet.getString("dni");
                // Crear un objeto Plaza a partir de los valores obtenidos
                Vehiculo ve = new Vehiculo(matricula, tipo, marca,modelo,anho,dni2);

                // Añadir el objeto Plaza a la lista
                vehiculos.add(ve);
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
        return vehiculos;
    }
    List<Vehiculo> obtenerVehiculos(String dni) {
        String query;
        PreparedStatement statement = null;
        List<Vehiculo> vehiculos = new ArrayList<>();
        // Crea una conexión a la base de datos
        Connection connection = this.getConexion();
        try {
            // Crear un objeto PreparedStatement con la consulta
            query = "SELECT * FROM Vehiculos WHERE DNI like ? and dni not in (select dni from usuarios where fechaveto is not NULL)";
            statement = connection.prepareStatement(query);
            // Establecer los parámetros de la consulta
            statement.setString(1, dni+"%");

            // Ejecutar la consulta y obtener un conjunto de resultados
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los resultados y crear un objeto Plaza por cada fila
            while (resultSet.next()) {
                // Obtener los valores de cada columna en la fila actual
                String matricula = resultSet.getString("matricula");
                TipoPlaza tipo = TipoPlaza.valueOf(resultSet.getString("tipo"));
                String marca = resultSet.getString("marca");
                String modelo= resultSet.getString("modelo");
                Integer anho= resultSet.getInt("anhomatriculacion");
                String dni2=resultSet.getString("dni");
                // Crear un objeto Plaza a partir de los valores obtenidos
                Vehiculo ve = new Vehiculo(matricula, tipo, marca,modelo,anho,dni2);

                // Añadir el objeto Plaza a la lista
                vehiculos.add(ve);
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
        // Devolver la lista de objetos 
        return vehiculos;
    }
List<Vehiculo> obtenerVehiculosNoAparcados() {
        String query;
        PreparedStatement statement = null;
        List<Vehiculo> vehiculos = new ArrayList<>();
        // Crea una conexión a la base de datos
        Connection connection = this.getConexion();
        try {
            // Crear un objeto PreparedStatement con la consulta
            query = "SELECT * FROM Vehiculos v where (v.dni not in (select dni from usuarios where fechaveto is not NULL)) and "
                    + "(v.matricula not in(select matriculavehiculo from aparcar where fechasalida is NULL))";
            statement = connection.prepareStatement(query);
            // Establecer los parámetros de la consulta

            // Ejecutar la consulta y obtener un conjunto de resultados
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los resultados y crear un objeto Plaza por cada fila
            while (resultSet.next()) {
                // Obtener los valores de cada columna en la fila actual
                String matricula = resultSet.getString("matricula");
                TipoPlaza tipo = TipoPlaza.valueOf(resultSet.getString("tipo"));
                String marca = resultSet.getString("marca");
                String modelo= resultSet.getString("modelo");
                Integer anho= resultSet.getInt("anhomatriculacion");
                String dni2=resultSet.getString("dni");
                // Crear un objeto Plaza a partir de los valores obtenidos
                Vehiculo ve = new Vehiculo(matricula, tipo, marca,modelo,anho,dni2);

                // Añadir el objeto Plaza a la lista
                vehiculos.add(ve);
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
        return vehiculos;
    }

List<Vehiculo> obtenerVehiculosNoAparcados(String dni) {
        String query;
        PreparedStatement statement = null;
        List<Vehiculo> vehiculos = new ArrayList<>();
        // Crea una conexión a la base de datos
        Connection connection = this.getConexion();
        try {
            // Crear un objeto PreparedStatement con la consulta
            query = "SELECT * FROM Vehiculos WHERE DNI like ? and dni not in (select dni from usuarios where fechaveto is not NULL) and "
                    + "(matricula not in(select matriculavehiculo from aparcar where fechasalida is NULL))";
            statement = connection.prepareStatement(query);
            // Establecer los parámetros de la consulta
            statement.setString(1, dni+"%");

            // Ejecutar la consulta y obtener un conjunto de resultados
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los resultados y crear un objeto Plaza por cada fila
            while (resultSet.next()) {
                // Obtener los valores de cada columna en la fila actual
                String matricula = resultSet.getString("matricula");
                TipoPlaza tipo = TipoPlaza.valueOf(resultSet.getString("tipo"));
                String marca = resultSet.getString("marca");
                String modelo= resultSet.getString("modelo");
                Integer anho= resultSet.getInt("anhomatriculacion");
                String dni2=resultSet.getString("dni");
                // Crear un objeto Plaza a partir de los valores obtenidos
                Vehiculo ve = new Vehiculo(matricula, tipo, marca,modelo,anho,dni2);

                // Añadir el objeto Plaza a la lista
                vehiculos.add(ve);
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
        // Devolver la lista de objetos 
        return vehiculos;
    }

        
    public String obtenerRol(String dni){
            String rol=null;
             String query;
            PreparedStatement statement = null;
            // Crea una conexión a la base de datos
            Connection connection = this.getConexion();
            try {
                // Crear un objeto PreparedStatement con la consulta
                query = "SELECT * FROM Usuarios WHERE DNI like ? ";
                statement = connection.prepareStatement(query);
                // Establecer los parámetros de la consulta
                statement.setString(1, dni);

                // Ejecutar la consulta y obtener un conjunto de resultados
                ResultSet resultSet = statement.executeQuery();

                // Recorrer los resultados y crear un objeto Plaza por cada fila
                while (resultSet.next()) {
                    // Obtener los valores de cada columna en la fila actual
                     rol = resultSet.getString("rol");

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
            return rol;
    }
    
    public boolean registrarUsuario(Usuario usuario){
        Connection con=this.getConexion();
        PreparedStatement stmUsuario=null;
        boolean exito = true;

        try {
            stmUsuario=con.prepareStatement("INSERT INTO Usuarios (dni, nombre, telefono, correo, fechaIngresoUSC, rol, fechaVeto, numeroInfracciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            stmUsuario.setString(1, usuario.getDni());
            stmUsuario.setString(2, usuario.getNombre());
            stmUsuario.setString(3, usuario.getTelefono());
            stmUsuario.setString(4, usuario.getCorreo());
            stmUsuario.setDate(5, java.sql.Date.valueOf(usuario.getFechaIngreso()));
            stmUsuario.setString(6, usuario.getRol().toString());
            if (usuario.getFechaVeto()!=null) {
                stmUsuario.setDate(7, java.sql.Date.valueOf(usuario.getFechaVeto()));
            } else {
                stmUsuario.setDate(7, null);

            }
            stmUsuario.setInt(8, usuario.getNumeroInfracciones());
            
            int filasAfectadas = stmUsuario.executeUpdate();
            
            for (Vehiculo vehiculo : usuario.getVehiculos()) {
                PreparedStatement stmVehiculo=null;
                try {
                    stmVehiculo=con.prepareStatement("INSERT INTO Vehiculos (matricula, tipo, marca, modelo, anhoMatriculacion, dni) VALUES (?, ?, ?, ?, ?, ?)");

                    stmVehiculo.setString(1, vehiculo.getMatricula());
                    stmVehiculo.setString(2, vehiculo.getTipo().toString());
                    stmVehiculo.setString(3, vehiculo.getMarca());
                    stmVehiculo.setString(4, vehiculo.getModelo());
                    stmVehiculo.setInt(5, vehiculo.getAnoMatriculacion());
                    stmVehiculo.setString(6, usuario.getDni());

                    filasAfectadas += stmVehiculo.executeUpdate();
                } 
                catch (SQLException e) {
                    exito=false;
                    System.out.println(e.getMessage());
                    this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
                } 
                finally {
                    try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
                }
            }
            
            } catch (SQLException e) {
                exito=false;
                System.out.println(e.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            } finally {
                try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        return exito;
    }
    /**
     * 
     * @param dni 
     * Suma unha infracción ao usuario cuxo dni é [dni]
     * FUNCIONA
     */
    public void registrarInfraccion(String dni){
        Connection con;
        con = this.getConexion();
        
        PreparedStatement stm=null;
        String consulta = "UPDATE Usuarios SET numeroinfracciones = numeroinfracciones +1 WHERE dni = ?";
        try{
            stm = con.prepareStatement(consulta);
            stm.setString(1, dni);
            stm.executeUpdate();
        }
         catch(SQLException error){
             System.out.println(error.getMessage());
        }finally{
            try{stm.close();}catch(SQLException e){System.out.println("No se pueden cerrar cursores");}
        }
    }
    /**
     * 
     * @param dni
     * Registra un veto na data na que se está automáticamente (é dicir, sen pasarlle a data)
     * FUNCIONA
     */
    public void registrarVeto(String dni){
        Connection con;
        con = this.getConexion();
        
        PreparedStatement stm = null;
        String consulta = "UPDATE Usuarios SET fechaveto = ? WHERE dni = ?";
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            stm = con.prepareStatement(consulta);
            stm.setString(2,dni);
            stm.setTimestamp(1, timestamp);
            stm.executeUpdate();
        }
          catch(SQLException error){
             System.out.println(error.getMessage());
        }finally{
            try{stm.close();}catch(SQLException e){System.out.println("No se pueden cerrar cursores");}
        }
    }
    /**
     * 
     * @param dni 
     * Pon a NULL a fechaveto do Usuario con dni [dni]
     * FUNCIONA
     */
    public void quitarVeto(String dni){
        Connection con;
        con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "UPDATE Usuarios SET fechaveto = NULL WHERE dni = ?";
        try{
            stm = con.prepareStatement(consulta);
            stm.setString(1,dni);
            stm.executeUpdate();
        }
          catch(SQLException error){
             System.out.println(error.getMessage());
        }finally{
            try{stm.close();}catch(SQLException e){System.out.println("No se pueden cerrar cursores");}
        }
    }
    /**
     * 
     * @param dni 
     * Pon o numero de infraccions a cero
     * FUNCIONA
     */
    public void quitarTodasInfracciones(String dni){
        Connection con;
        con = this.getConexion();
        
        PreparedStatement stm=null;
        String consulta = "UPDATE Usuarios SET numeroinfracciones = 0 WHERE dni = ?";
        try{
            stm = con.prepareStatement(consulta);
            stm.setString(1, dni);
            stm.executeUpdate();
        }
         catch(SQLException error){
             System.out.println(error.getMessage());
        }finally{
            try{stm.close();}catch(SQLException e){System.out.println("No se pueden cerrar cursores");}
        }
    }
    /**
     * 
     * @param dni
     * @return -1 si ocorreu algún fallo ou un numero do 0 ao 5 
     * FUNCIONA
     */
    public int mostrarNumeroInfracciones(String dni){
        Connection con;
        int dev;
        dev = -1;
        con = this.getConexion();
        PreparedStatement stm = null;
        String consulta = "SELECT numeroinfracciones FROM Usuarios WHERE dni = ?";
        try{
            stm = con.prepareStatement(consulta);
            stm.setString(1, dni);
            ResultSet resultado  = stm.executeQuery();
            if(resultado.next()){ //poño un if e non un while porque en principio solo hai unha tupla
                dev = resultado.getInt("numeroinfracciones");
            }
        } catch(SQLException error){
             System.out.println(error.getMessage());
        }finally{
            try{stm.close();}catch(SQLException e){System.out.println("No se pueden cerrar cursores");}
        }      
        return dev;
    }
    /**
     * 
     * @param matricula
     * @return un Usuario propietario do vehiculo con matricula [matricula]
     */
    public Usuario obtenerUsuario(String matricula){
        Connection con;
        con=this.getConexion();
        PreparedStatement stm = null;
        LocalDate fechaveto = null;
        LocalDate fechaingreso = null;
        
        Usuario usuario = null;
        
        String consulta = "SELECT u.* FROM vehiculos v, usuarios u WHERE v.dni = u.dni AND v.matricula = ?";
        try{
            stm = con.prepareStatement(consulta);
            stm.setString(1,matricula);
            ResultSet resultado = stm.executeQuery();
            if(resultado.next()){
                RolUsuario rolUsuario = null;
                if(resultado.getString("rol").equals("Administracion"))
                    rolUsuario = RolUsuario.Administracion;
                else if (resultado.getString("rol").equals("Docente")) {
                    rolUsuario = RolUsuario.Docente;
                }
                else if(resultado.getString("rol").equals("Alumno")){
                    rolUsuario = RolUsuario.Alumno;
                }
                else if(resultado.getString("rol").equals("noUSC")){
                    rolUsuario = RolUsuario.noUSC;
                }
                if (resultado.getTimestamp("fechaveto")!=null) {
                    fechaveto = resultado.getTimestamp("fechaveto").toLocalDateTime().toLocalDate();
                }
                 if (resultado.getTimestamp("fechaingresousc")!=null) {
                    fechaingreso = resultado.getTimestamp("fechaingresousc").toLocalDateTime().toLocalDate();
                }
                usuario = new Usuario(resultado.getString("dni"),resultado.getString("nombre"),resultado.getString("telefono"),
                resultado.getString("correo"), fechaingreso, rolUsuario,
                        fechaveto,resultado.getInt("numeroinfracciones"));
            }
        }catch(SQLException error){
             System.out.println(error.getMessage());
        }finally{
            try{stm.close();}catch(SQLException e){System.out.println("No se pueden cerrar cursores");}
        }  
        return usuario;
    }
}