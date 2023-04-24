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

        String sql = "DELETE FROM persona WHERE idPersona=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getId());
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
}
