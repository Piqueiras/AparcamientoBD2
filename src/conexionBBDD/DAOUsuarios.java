/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionBBDD;

import java.sql.*;
import aplication.*;
import java.time.LocalDateTime;
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
            
            //consulta corta
            //stmUsuario=con.prepareStatement("select dni "+
            //                                "from Usuarios "+
            //                                "where dni like ? ");
            
            stmUsuario.setString(1, dni);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
                {
                    //consulta completa
                    resultado = new Usuario(rsUsuario.getString("dni"), rsUsuario.getString("nombre"),
                            rsUsuario.getString("telefono"), rsUsuario.getString("correo"),
                            rsUsuario.getDate("fechaIngresoUSC").toLocalDate(), aplication.RolUsuario.valueOf(rsUsuario.getString("rol")),
                            rsUsuario.getInt("numeroInfracciones"));
                    
                    if (rsUsuario.getObject("fechaVeto") != null) {
                        resultado.setFechaVeto(rsUsuario.getDate("fechaVeto").toLocalDate());
                    }
                    
                    //consulta corta
                    //resultado = new Usuario(rsUsuario.getString("dni"));
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
        Aparcar aparcarActual;
        Connection con;
        PreparedStatement stmVehiculo = null;
        ResultSet rsVehiculo;
        PreparedStatement stmAparcar=null;
        ResultSet rsAparcar;
        String partes[];
        String aux;
        int cAct;
        int cSat;

        con=this.getConexion();
        
        String consulta = "select matricula "
                         + "from Vehiculos "
                         + "where dni like \'" + dni + "\'";
        
        try{
            stmVehiculo = con.prepareStatement(consulta);
            rsVehiculo = stmVehiculo.executeQuery();
            
            while (rsVehiculo.next()) {
                vehiculoActual = new Vehiculo(rsVehiculo.getString("matricula"));
                
                consulta = "select matriculaVehiculo, codigoPlaza, idAparcamiento, fechaentrada, fechasalida " +
                           "from aparcar "+
                           "where matriculaVehiculo like \'";
                
                if (mat.isEmpty()) {
                    mat = vehiculoActual.getMatricula();
                }
                consulta = consulta + mat + "\'";
                
                if (!pza.isEmpty()) {
                    consulta = consulta + " and codigoPlaza = " + pza + " ";
                }
                if (!ap.isEmpty()) {
                    consulta = consulta + " and idAparcamiento like \'" + ap + "\'";
                }
                /*if (!cMax.isEmpty()) {
                    consulta = consulta + " and precio <= " + cMax;
                }
                if (!cMin.isEmpty()) {
                    consulta = consulta + " and precio >= " + cMin;
                }
                if (!dMax.isEmpty()) {
                    aux = dMax.replaceAll(" ", "");
                    partes = aux.split("[hms]");
                    consulta = consulta + " and tiempo <= \'" + partes[0] + "-" + partes[1] + "-" + partes[2] + "\'";
                }
                if (!dMin.isEmpty()) {
                    aux = dMin.replaceAll(" ", "");
                    partes = aux.split("[hms]");
                    consulta = consulta + " and tiempo >= \'" + partes[0] + "-" + partes[1] + "-" + partes[2] + "\'";
                }*/
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
                            aparcarActual = new Aparcar(rsAparcar.getString("matriculaVehiculo"), rsAparcar.getInt("codigoPlaza"),
                                                  rsAparcar.getString("idAparcamiento"), rsAparcar.getTimestamp("fechaentrada").toLocalDateTime(),
                                                  rsAparcar.getTimestamp("fechasalida").toLocalDateTime(), this.getFachadaAplicacion());
                            
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
        Reservar reservarActual;
        Connection con;
        PreparedStatement stmVehiculo = null;
        ResultSet rsVehiculo;
        PreparedStatement stmReservar=null;
        ResultSet rsReservar;
        String partes[];
        String aux;
        int cAct;
        int cSat;

        con=this.getConexion();
        
        String consulta = "select matricula "
                         + "from Vehiculos "
                         + "where dni like \'" + dni + "\'";
        
        try{
            stmVehiculo = con.prepareStatement(consulta);
            rsVehiculo = stmVehiculo.executeQuery();
            
            while (rsVehiculo.next()) {
                vehiculoActual = new Vehiculo(rsVehiculo.getString("matricula"));
                
                consulta = "select matriculaVehiculo, codigoPlazaReserva, idAparcamiento, fechaInicio, fechaFin " +
                           "from reservar "+
                           "where matriculaVehiculo like \'";
                
                if (mat.isEmpty()) {
                    mat = vehiculoActual.getMatricula();
                }
                consulta = consulta + mat + "\'";
                
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
                            reservarActual = new Reservar(rsReservar.getString("matriculaVehiculo"), rsReservar.getInt("codigoPlazaReserva"),
                                                  rsReservar.getString("idAparcamiento"), rsReservar.getTimestamp("fechaInicio").toLocalDateTime(),
                                                  rsReservar.getTimestamp("fechaFin").toLocalDateTime(), this.getFachadaAplicacion());
                            
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

    public void registrarUsuario(Usuario usuario){
        Connection con=this.getConexion();
        PreparedStatement stmUsuario=null;

        try {
            stmUsuario=con.prepareStatement("INSERT INTO Usuarios (dni, nombre, telefono, correo, fechaIngresoUSC, rol, fechaVeto, numeroInfracciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            stmUsuario.setString(1, usuario.getDni());
            stmUsuario.setString(2, usuario.getNombre());
            stmUsuario.setString(3, usuario.getTelefono());
            stmUsuario.setString(4, usuario.getCorreo());
            stmUsuario.setDate(5, java.sql.Date.valueOf(usuario.getFechaIngreso()));
            stmUsuario.setString(6, usuario.getRol().toString());
            stmUsuario.setDate(7, java.sql.Date.valueOf(usuario.getFechaVeto()));
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

                    filasAfectadas += stmUsuario.executeUpdate();
                } 
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                    this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
                } 
                finally {
                    try {stmVehiculo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
                }
            }
            
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
            } finally {
                try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        
    }
}