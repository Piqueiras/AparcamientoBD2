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
    
    /**
     * Este metodo obtiene los datos de un usuario en la base de datos, a traves de su dni. Crea una instancia de Usuario con estos datos y la devuelve.
     * Sera usado para comprobar la autentificacion al comienzo de la aplicacion.
     * @param dni - DNI del usuario que se quiere verificar en la base de datos.
     * @return Usuario - instancia de Usuario con los datos del usuario asociado al dni.
     */
    public Usuario validarUsuario(String dni){
        Usuario resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        //Se obtiene la conexion a la base de datos
        con=this.getConexion();
        
        try {
            //Con esta consulta, se obtienen todos los datos del usuario
            stmUsuario=con.prepareStatement("select dni, nombre, telefono, correo, fechaIngresoUSC, rol, fechaVeto, numeroInfracciones "+
                                            "from Usuarios "+
                                            "where dni like ? ");
            
            //Se ejecuta la consulta en la base de datos y se obtiene el resultSet
            stmUsuario.setString(1, dni);
            rsUsuario=stmUsuario.executeQuery();
            
            if (rsUsuario.next())
                {
                    //Se crea el usuario con los datos. Algunos datos pueden ser null y causar excepciones, por lo que no se incluyen todos en el constructor.
                    resultado = new Usuario(rsUsuario.getString("dni"), rsUsuario.getString("nombre"),
                            rsUsuario.getString("telefono"), rsUsuario.getString("correo"),
                            aplication.RolUsuario.valueOf(rsUsuario.getString("rol")),
                            rsUsuario.getInt("numeroInfracciones"));
                    
                    //fechaIngresoUSC y fechaVeto pueden ser null, lo que causaria problemas con toLocalDate(). Por eso, hay que tratar estos errores.
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
        //Se devuelve la instancia de Usuario
        return resultado;
    }
    
    /** 
     * Este metodo obtiene todas las tuplas de la tabla Aparcar de la base de datos que cumplan las condiciones especificadas.
     * Los datos de cada tupla se guardan en una instancia de Aparcar, y los resultados se devuelven en una lista de Aparcar.
     * Todos los parametros pueden ser null. El metodo usara para la busqueda solo los parametros que no lo sean.
     * @param dni - DNI del usuario cuyos aparcamientos se quieren obtener.
     * @param mat - Matricula del vehiculo cuyos aparcamientos se quieren obtener.
     * @param pza - Numero de la plaza cuyos aparcamientos se quieren obtener.
     * @param ap - Id del aparcamiento cuyos aparcamientos se quieren obtener.
     * @param cMax - Coste maximo de los aparcamientos.
     * @param cMin - Coste minimo de los aparcamientos.
     * @param dMax - Duracion maxima de los aparcamientos.
     * @param dMin - Duracion minima de los aparcamientos.
     * @param fMax - Fecha maxima de los aparcamientos.
     * @param fMin - Fecha minima de los aparcamientos.
     * @return List<Aparcar> - Lista con los datos de las tuplas de la tabla Aparcar almacenados en instancias de Aparcar.
     */
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
        
        //Se obtiene la consexión con la base de datos
        con=this.getConexion();
        
        //Con esta consulta, obtenemos los vehiculos de un determinado usuario, usando su dni.
        //Posteriormente, obtendremos todos los aparcamientos que ha realizado cada vehiculo que se obtiene en esta consulta.
        String consulta = "select dni, matricula "
                         + "from Vehiculos "
                         + "where dni like ";
                
        //Si se introduce un dni en la caja de busqueda, se busca con ese dni. Si no, se obtienen todas las tuplas de la tabla.
        if (!dni.isEmpty()) {
            consulta = consulta + "\'" + dni + "\'";    //Busqueda por dni concreto
        }
        else {
            consulta = consulta + "\'%\'";              //Busqueda con cualquier dni
        }
        
        //Busqueda por matricula, en caso de que se haya introducido una
        if (!mat.isEmpty()){
            consulta = consulta + "and matricula like \'" + mat + "\'";
        }
        
        try{
            //Ejecutamos la consulta. El resultado sera un conjunto de vehiculos, por lo que llamamos al resultSet rsVehiculo.
            stmVehiculo = con.prepareStatement(consulta);
            rsVehiculo = stmVehiculo.executeQuery();
            
            //Ahora debemos ejecutar un bucle sobre el resultSet para obtener los aparcamientos de cada vehiculo.
            //Cada iteracion del bucle obtendra los aparcamientos de un vehiculo.
            while (rsVehiculo.next()) {
                //Necesitaremos el rol del duenho del vehiculo, para asi poder calcular el precio adecuado. Creamos pues el usuario.
                usuario = new Usuario(rsVehiculo.getString("dni"));
                
                //Creamos un nuevo vehiculo con la matricula dada y el usuario que acabamos de crear.
                //Recordemos que si se ha introducido una matricula en los parametros de busqueda, se creara con esa, pues sera el unico resultado en el resultSet
                //Usaremos este vehiculo para obtener sus aparcamientos.
                vehiculoActual = new Vehiculo(rsVehiculo.getString("matricula"), usuario);
                
                //Obtenemos el rol del duenho del vehiculo.
                consulta = "select rol from Usuarios where dni like \'" + usuario.getDni() + "\'";
                
                //Ejecutamos la consulta. El resultado sera simplemente el rol del usuario
                stmUsuario = con.prepareStatement(consulta);
                rsUsuario = stmUsuario.executeQuery();
                rsUsuario.next();
                
                //Guardamos el rol que acabamos de obtener
                rol = RolUsuario.valueOf(rsUsuario.getString("rol"));
                
                //Ahora comenzaremos el proceso para obtener los aparcamientos del vehiculo que hemos creado anteriormente.
                //Queremos todos los datos de la tabla. Comenzamos buscando con la matricula, pues esta sera siempre un parametro de busqueda.
                //La matricula con la que buscaremos siempre sera la del vehiculo que hemos creado.
                consulta = "select matriculaVehiculo, codigoPlaza, idAparcamiento, fechaentrada, fechasalida " +
                           "from aparcar "+
                           "where matriculaVehiculo like \'" + vehiculoActual.getMatricula() + "\'";
                
                //Debemos comprobar si se han introducido otros parametros de busqueda. Comenzamos con los no calculados.
                //Simplemente, si se ha introducido algun parametro, se anhade la condicion a la consulta.
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
                
                
                try {
                    //Ahora ejecutamos la nueva consulta para obtener los aparcamientos, con todas las restricciones especificadas
                    stmAparcar=con.prepareStatement(consulta);
                    rsAparcar=stmAparcar.executeQuery();
                    
                    //Ejecutamos un bucle para todas las tuplas de aparcamientos obtenidas
                    while (rsAparcar.next()) {
                        //Creamos una instancia de Aparcar que guarde todos los datos de la tupla
                        aparcarActual = new Aparcar(vehiculoActual, rsAparcar.getInt("codigoPlaza"),
                                              rsAparcar.getString("idAparcamiento"),
                                                rsAparcar.getTimestamp("fechaentrada").toLocalDateTime(), rol);
                        
                        //fechasalida puede ser null. Si se diese el caso, .toLocalDateTime() lanzaria una excepcion y no se podría seguir con el proceso.
                        //Para evitarlo, controlamos que sea null, y solo si no lo es realizamos la conversion
                        if (rsAparcar.getObject("fechasalida") != null) {
                            aparcarActual.setFechaSalida(rsAparcar.getTimestamp("fechasalida").toLocalDateTime());
                        }
                        
                        //Ahora tenemos que aplicar las restricciones de coste y duracion. Como estos parametros no estan en la tabla, no se pueden aplicar en la consulta.
                        //En su lugar, se controlan aqui. Al crear aparcar, ya se han calculado tanto el precio como la duracion.
                        cAct = 0; //numero de condiciones de coste y duracion activas (es decir, numero de parametros de coste y duracion usados para buscar)
                        cSat = 0; //numero de condiciones de coste y duracion que se cumplen

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
                        
                        //Finalmente, si se cumplen todas las condiciones, se anhade la instancia de aparcar a la lista
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
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
          try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        //Se devuelve una lista con todas las tuplas de aparcar que satisfacen todas las condiciones
        return resultado;
    }
    
    /**
     * Este metodo obtiene todas las tuplas de la tabla Reservar de la base de datos que cumplan las condiciones especificadas.
     * Los datos de cada tupla se guardan en una instancia de Reservar, y los resultados se devuelven en una lista de Reservar.
     * Todos los parametros pueden ser null. El metodo usara para la busqueda solo los parametros que no lo sean.
     * @param dni - DNI del usuario cuyas reservas se quieren obtener.
     * @param mat - Matricula del vehiculo cuyas reservas se quieren obtener.
     * @param pza - Numero de la plaza cuyas reservas se quieren obtener.
     * @param ap - Id del aparcamiento cuyas reservas se quieren obtener.
     * @param cMax - Coste maximo de las reservas.
     * @param cMin - Coste minimo de las reservas.
     * @param dMax - Duracion maxima de las reservas.
     * @param dMin - Duracion minima de las reservas.
     * @param fMax - Fecha maxima de las reservas.
     * @param fMin - Fecha minima de las reservas.
     * @return List<Reservar> - Lista con los datos de las tuplas de la tabla Aparcar almacenados en instancias de Aparcar.
     */
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

        //Se obtiene la consexión con la base de datos
        con=this.getConexion();
        
        //Con esta consulta, obtenemos los vehiculos de un determinado usuario, usando su dni.
        //Posteriormente, obtendremos todos las reservas que ha realizado cada vehiculo que se obtiene en esta consulta.
        String consulta = "select dni, matricula "
                         + "from Vehiculos "
                         + "where dni like ";
        
        //Si se introduce un dni en la caja de busqueda, se busca con ese dni. Si no, se obtienen todas las tuplas de la tabla.
        if (!dni.isEmpty()) {
            consulta = consulta + "\'" + dni + "\'";    //Busqueda por dni concreto
        }
        else {
            consulta = consulta + "\'%\'";              //Busqueda con cualquier dni
        }
        
        //Busqueda por matricula, en caso de que se haya introducido una
        if (!mat.isEmpty()){
            consulta = consulta + "and matricula like \'" + mat + "\'";
        }
        
        try{
            //Ejecutamos la consulta. El resultado sera un conjunto de vehiculos, por lo que llamamos al resultSet rsVehiculo.
            stmVehiculo = con.prepareStatement(consulta);
            rsVehiculo = stmVehiculo.executeQuery();
            
            //Ahora debemos ejecutar un bucle sobre el resultSet para obtener las reservas de cada vehiculo.
            //Cada iteracion del bucle obtendra las reservas de un vehiculo.
            while (rsVehiculo.next()) {
                //Necesitaremos el rol del duenho del vehiculo, para asi poder calcular el precio adecuado. Creamos pues el usuario.
                usuario = new Usuario(rsVehiculo.getString("dni"));
                
                //Creamos un nuevo vehiculo con la matricula dada y el usuario que acabamos de crear.
                //Recordemos que si se ha introducido una matricula en los parametros de busqueda, se creara con esa, pues sera el unico resultado en el resultSet
                //Usaremos este vehiculo para obtener sus reservas.
                vehiculoActual = new Vehiculo(rsVehiculo.getString("matricula"), usuario);
                
                //Obtenemos el rol del duenho del vehiculo.
                consulta = "select rol from Usuarios where dni like \'" + usuario.getDni() + "\'";
                
                //Ejecutamos la consulta. El resultado sera simplemente el rol del usuario
                stmUsuario = con.prepareStatement(consulta);
                rsUsuario = stmUsuario.executeQuery();
                rsUsuario.next();
                
                //Guardamos el rol que acabamos de obtener
                rol = RolUsuario.valueOf(rsUsuario.getString("rol"));
                
                //Ahora comenzaremos el proceso para obtener las reservas del vehiculo que hemos creado anteriormente.
                //Queremos todos los datos de la tabla. Comenzamos buscando con la matricula, pues esta sera siempre un parametro de busqueda.
                //La matricula con la que buscaremos siempre sera la del vehiculo que hemos creado.
                consulta = "select matriculaVehiculo, codigoPlazaReserva, idAparcamiento, fechaInicio, fechaFin " +
                           "from reservar "+
                           "where matriculaVehiculo like \'" + vehiculoActual.getMatricula() + "\'";
                
                //Debemos comprobar si se han introducido otros parametros de busqueda. Comenzamos con los no calculados.
                //Simplemente, si se ha introducido algun parametro, se anhade la condicion a la consulta.
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
                
                try {
                    //Ahora ejecutamos la nueva consulta para obtener las reservas, con todas las restricciones especificadas
                    stmReservar=con.prepareStatement(consulta);
                    rsReservar=stmReservar.executeQuery();

                    //Ejecutamos un bucle para todas las tuplas de reservas obtenidas
                    while (rsReservar.next()) {
                        //Creamos una instancia de reservar que guarde todos los datos de la tupla
                        reservarActual = new Reservar(vehiculoActual, rsReservar.getInt("codigoPlazaReserva"),
                                              rsReservar.getString("idAparcamiento"),
                                                rsReservar.getTimestamp("fechaInicio").toLocalDateTime(),
                                                 rsReservar.getTimestamp("fechaFin").toLocalDateTime(), rol);

                        //fechafin puede ser null. Si se diese el caso, .toLocalDateTime() lanzaria una excepcion y no se podría seguir con el proceso.
                        //Para evitarlo, controlamos que sea null, y solo si no lo es realizamos la conversion
                        if (rsReservar.getObject("fechaFin") != null) {
                            reservarActual.setFechaSalida(rsReservar.getTimestamp("fechaFin").toLocalDateTime());
                        }
                        
                        //Ahora tenemos que aplicar las restricciones de coste y duracion. Como estos parametros no estan en la tabla, no se pueden aplicar en la consulta.
                        //En su lugar, se controlan aqui. Al crear reservar, ya se han calculado tanto el precio como la duracion.
                        cAct = 0; //numero de condiciones de coste y duracion activas (es decir, numero de parametros de coste y duracion usados para buscar)
                        cSat = 0; //numero de condiciones de coste y duracion que se cumplen
                        
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
                        //Finalmente, si se cumplen todas las condiciones, se anhade la instancia de reservar a la lista
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
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
          try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        //Se devuelve una lista con todas las tuplas de reservar que satisfacen todas las condiciones
        return resultado;
    }

    /**
     * Devuelve todos los vehiculos registrados en la base de datos de usuarios no vetados
     * @return Lista con dichos vehiculos
     */
    public List<Vehiculo> obtenerVehiculos() {
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

            // Recorrer los resultados y crear un objeto Vehiculo por cada fila
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

                // Añadir el objeto Vehiculo a la lista
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
        // Devolver la lista de objetos Vehiculos
        return vehiculos;
    }

    /**
     *     * Devuelve todos los vehiculos registrados en la base de datos de usuarios no vetados
     * @param dni del usuario del vehiculo
     * @return Lista con dichos vehiculos
     */
    public List<Vehiculo> obtenerVehiculos(String dni) {
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

    /**
* Devuelve todos los vehiculos registrados en la base de datos, de usuarios no vetados y no aparcados
     * @return Lista con dichos vehiculos
     */
    public List<Vehiculo> obtenerVehiculosNoAparcados() {
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
    /**
     *     * Devuelve todos los vehiculos registrados en la base de datos de usuarios no vetados y no aparcados
     * @param dni del usuario del vehiculo
     * @return Lista con dichos vehiculos
     */
public List<Vehiculo> obtenerVehiculosNoAparcados(String dni) {
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

    /**
     *Devuelve el rol del usuario registrado en la base de datos con el dni pasado
     * @param dni
     * @return rol
     */
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
    /**
     * 
     * @return unha lista cos usuarios que teñen veto actual (orientado a actualización dos vetos)
     */
        public ArrayList<Usuario>  buscarUsuariosConVeto(){
        Connection con;
        con = this.getConexion();
        
        LocalDate fechaveto = null;
        LocalDate fechaingreso = null;
        
        ArrayList<Usuario> dev = new ArrayList<>();
        Statement stm=null;
        String consulta = "SELECT * FROM Usuarios WHERE fechaveto is not NULL";
        try{
            stm = con.createStatement();
            ResultSet resultado = stm.executeQuery(consulta);

            while(resultado.next()){
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
                dev.add(new Usuario(resultado.getString("dni"),resultado.getString("nombre"),resultado.getString("telefono"),
                resultado.getString("correo"), fechaingreso, rolUsuario,
                        fechaveto,resultado.getInt("numeroinfracciones")));
            }
        }
         catch(SQLException error){
             System.out.println(error.getMessage());
        }finally{
            try{stm.close();}catch(SQLException e){System.out.println("No se pueden cerrar cursores");}
            return dev;
        }
    }
    
    /**
     * Hace  commit a la transaccion actual en la base de datos.
     */
    public void commitTransaction(){
        java.sql.Connection con;
        con = this.getConexion();
        
        Statement stm=null;
        String consulta = "commit;";
        try{
            stm = con.createStatement();
            stm.executeQuery(consulta);
        }
         catch(SQLException error){
             if(error.getErrorCode() == 4001){ //Comprueba que no se haya producido un error por realizar varias transacciones a la vez
                 System.out.println("Prodúxose un error polo isolation level serializable");  
             }else{
             System.out.println(error.getMessage());
             }
        }finally{
            try{stm.close();}catch(SQLException e){System.out.println("No se pueden cerrar cursores");}
        }
    }

    /**
     * Comienza una nueva transaccion en la base de datos
     */
    public void beginTransaction(){
        java.sql.Connection con;
        con = this.getConexion();
        
        Statement stm=null;
        String consulta = "begin transaction;";
        try{
            stm = con.createStatement();
            stm.executeQuery(consulta);
        }
         catch(SQLException error){
             System.out.println(error.getMessage());
        }finally{
            try{stm.close();}catch(SQLException e){System.out.println("No se pueden cerrar cursores");}////FALTAME COLLER A EXCEPCION
        }
    }
    
    
    public void beginTransactionSerializable(){
        java.sql.Connection con;
        con = this.getConexion();
        
        Statement stm=null;
        String consulta = "begin transaction isolation level serializable;";
        try{
            stm = con.createStatement();
            stm.executeQuery(consulta);
        }
         catch(SQLException error){
             System.out.println(error.getMessage());
        }finally{
            try{stm.close();}catch(SQLException e){System.out.println("No se pueden cerrar cursores");}////FALTAME COLLER A EXCEPCION
        }
    }
}