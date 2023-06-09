/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.sql.SQLIntegrityConstraintViolationException;


/**
 *
 * @author DHANI
 */
public class ClienteDTO extends Conexion {
    
    public boolean registrarCliente(Cliente cl) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sqlCliente = "INSERT INTO persona (nombre, apellido, cedula, telefono, email, usuario, contraseña, direccion, Vehiculo_idVehiculo) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            con.setAutoCommit(false); // Iniciar una transacción
            ps = con.prepareStatement(sqlCliente);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getApellido());
            ps.setString(3, cl.getCedula());
            ps.setString(4, cl.getTelefono());
            ps.setString(5, cl.getEmail());
            ps.setString(6, cl.getUsuario());
            ps.setString(7, cl.getContrasena());
            ps.setString(8, cl.getDireccion());
            ps.setInt(9, cl.getId_vehiculo());
            ps.executeUpdate(); // Usar executeUpdate() en lugar de execute()
            con.commit(); // Confirmar la transacción
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            try {
                con.rollback(); // Revertir la transacción en caso de excepción
            } catch (SQLException e1) {
                System.err.println(e1);
            }
            return false;
        } finally {
            try {
                con.setAutoCommit(true); // Restablecer la configuración de AutoCommit
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean modificarCliente(Cliente cl) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "UPDATE persona SET nombre=?, apellido=?, cedula=?, telefono=?, email=?, usuario=?, contraseña=?, direccion=?, Vehiculo_idVehiculo=? WHERE idPersona=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getApellido());
            ps.setString(3, cl.getCedula());
            ps.setString(4, cl.getTelefono());
            ps.setString(5, cl.getEmail());
            ps.setString(6, cl.getUsuario());
            ps.setString(7, cl.getContrasena());
            ps.setString(8, cl.getDireccion());
            ps.setInt(9, cl.getId_vehiculo());
            ps.setInt(10, cl.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    public boolean eliminar(Cliente cl) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        //Query que borra solo persona
        String sql = "DELETE FROM persona WHERE idPersona=? ";
        

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getId());
            //ps.setInt(2, cl.getId());
            ps.execute();
            return true;
        }catch (SQLIntegrityConstraintViolationException e) {
            return false;
        }catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    public List listarClientes() {
        ResultSet rs;
        PreparedStatement ps = null;
        List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM persona WHERE rol IS NULL AND cargo IS NULL");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setApellido(rs.getString(3));
                cli.setCedula(rs.getString(4));
                cli.setTelefono(rs.getString(5));
                cli.setEmail(rs.getString(6));
                cli.setUsuario(rs.getString(7));
                cli.setContrasena(rs.getString(8));
                cli.setDireccion(rs.getString(9));
                cli.setId_vehiculo(rs.getInt(10));
                datos.add(cli);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    public Cliente consultaCliente(int idC){
        Cliente cli = new Cliente();
        //Cliente cli = null;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM persona WHERE idPersona=?");
            ps.setInt(1, idC);
            rs = ps.executeQuery();
            /**
            while (rs.next()) {
                //Cliente cli = new Cliente();
                cli.setId(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setApellido(rs.getString(3));
                cli.setCedula(rs.getString(4));
                cli.setTelefono(rs.getString(5));
                cli.setEmail(rs.getString(6));
                cli.setUsuario(rs.getString(7));
                cli.setContrasena(rs.getString(8));
                cli.setDireccion(rs.getString(9));
                cli.setId_vehiculo(rs.getInt(10));
                //datos.add(cli);
            }
            * **/
            if (rs.next()) {
                // Obtener los datos del resultado y crear un objeto Cliente
                cli.setId(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setApellido(rs.getString(3));
                cli.setCedula(rs.getString(4));
                cli.setTelefono(rs.getString(5));
                cli.setEmail(rs.getString(6));
                cli.setUsuario(rs.getString(7));
                cli.setContrasena(rs.getString(8));
                cli.setDireccion(rs.getString(9));
                if(rs.getInt(10)==0){
                    cli.setId_vehiculo(-1);
                }else{
                    cli.setId_vehiculo(rs.getInt(10)); 
                }
            }else{
                System.out.println("no hay resultados");
            }
        } catch (Exception e) {
        }
        //return datos;
        return cli;
    }
    public Set<String> obtenerPlacas() {
        ResultSet rs;
        PreparedStatement ps = null;
        Set<String> datos = new LinkedHashSet<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM vehiculo");
            rs = ps.executeQuery();
            while (rs.next()) {
                String placa = rs.getString(2);
                datos.add(placa);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    public int verificarAuto(String placa){
        int id;
        id=0;
        Vehiculo veh = new Vehiculo();
        //Cliente cli = null;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM vehiculo WHERE placa=?");
            ps.setString(1, placa);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Obtener los datos del resultado y crear un objeto Cliente
                veh.setId(rs.getInt(1));
                veh.setPlaca(rs.getString(2));
                veh.setTipo(rs.getString(3));
                veh.setEstado(rs.getString(4));
                veh.setMotivoIngreso(rs.getString(5));
                veh.setFechaIngreso(rs.getDate(6));
                veh.setFechaEntrega(rs.getDate(7));
                id = veh.getId();
            } else {
                System.out.println("no hay resultados");
                id = 0;
            }
        } catch (Exception e) {
        }
        
        return id;
    }
    public Vehiculo consultaVehiculo(int idC) {
        Vehiculo vh = new Vehiculo();
        //Cliente cli = null;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM vehiculo WHERE idVehiculo=?");
            ps.setInt(1, idC);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Obtener los datos del resultado y crear un objeto Cliente
                vh.setId(rs.getInt(1));
                vh.setPlaca(rs.getString(2));
                vh.setTipo(rs.getString(3));
                vh.setEstado(rs.getString(4));
                vh.setMotivoIngreso(rs.getString(5));
                vh.setFechaIngreso(rs.getDate(6));
                vh.setFechaEntrega(rs.getDate(7));
            } else {
                System.out.println("no hay resultados");
            }
        } catch (Exception e) {
        }
        //return datos;
        return vh;
    }
    public boolean verificarVehiculoAsociado(int idVehiculo){
        //Cliente cli = null;
        boolean resultado;
        resultado = false;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT COUNT(*) FROM persona WHERE Vehiculo_idVehiculo=?");
            ps.setInt(1, idVehiculo);
            rs = ps.executeQuery();
            rs.next();
            int registros = rs.getInt(1);
            if (registros > 0) {
                resultado = true;
                System.out.println("Vehiculo ya asociado");
            }else{
                System.out.println("Vehiculo no asociado");
            }
        } catch (Exception e) {
        }finally{
            //con.close();
        }
        //return datos;
        return resultado;
    }
    public boolean validarActualizacionALT(int idVehiculo, int idCliente){
        boolean resultado = false;
        ResultSet rs;
        PreparedStatement ps = null;
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT COUNT(*) FROM persona WHERE Vehiculo_idVehiculo = ? AND idPersona != ? AND (idPersona, Vehiculo_idVehiculo) NOT IN ((?, ?));");
            ps.setInt(1, idVehiculo);
            ps.setInt(2, idCliente);
            ps.setInt(3, idCliente);
            ps.setInt(4, idVehiculo);

            // Ejecutar la consulta y obtener el resultado
            rs = ps.executeQuery();

            // Leer el resultado
            rs.next();
            int numClientes = rs.getInt(1);

            // Verificar si el nuevo vehículo está asociado a otro cliente
            if (numClientes == 0) {
                System.out.println("Se puede actualizar el valor de idVehiculo.");
                resultado = true;
            } else {
                System.out.println("El nuevo valor de idVehiculo ya está asociado a otro cliente.");
            }

            // Cerrar la conexión y liberar los recursos
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public boolean validarActualizacion(int idCliente, int idVehiculo){
        boolean resultado = false;
        ResultSet rs;
        PreparedStatement ps = null;
        try {
            Connection con = getConexion();
            //query con error
            //ps = con.prepareStatement("SELECT COUNT(*) FROM persona WHERE Vehiculo_idVehiculo = ? AND idPersona != ?");
            ps = con.prepareStatement("SELECT COUNT(*) as count FROM Persona WHERE Vehiculo_idVehiculo = ? AND idPersona != ?");
            ps.setInt(1, idVehiculo);
            ps.setInt(2, idCliente);

            // Ejecutar la consulta y obtener el resultado
            rs = ps.executeQuery();

            // Leer el resultado
            rs.next();
            int numClientes = rs.getInt(1);

            // Verificar si el nuevo vehículo está asociado a otro cliente
            if (numClientes == 0) {
                //System.out.println("Se puede actualizar el valor de idVehiculo.");
                resultado = true;
            } else {
                //System.out.println("El nuevo valor de idVehiculo ya está asociado a otro cliente. ");
            }
            //System.out.println("idVehiculo: "+idVehiculo+" idCliente:"+idCliente);

            // Cerrar la conexión y liberar los recursos
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

}
