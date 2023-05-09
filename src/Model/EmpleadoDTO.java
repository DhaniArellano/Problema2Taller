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
import java.util.List;
import Utils.Encoder;
import Utils.Validador;

/**
 *
 * @author DHANI
 */
public class EmpleadoDTO extends Conexion {
    
    public boolean registrarEmpleado(Empleado em) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sqlCliente = "INSERT INTO persona (nombre, apellido, cedula, telefono, email, usuario, contraseña, rol, cargo, Vehiculo_idVehiculo) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sqlCliente);
            ps.setString(1, em.getNombre());
            ps.setString(2, em.getApellido());
            ps.setString(3, em.getCedula());
            ps.setString(4, em.getTelefono());
            ps.setString(5, em.getEmail());
            ps.setString(6, em.getUsuario());
            ps.setString(7, em.getContrasena());
            ps.setString(8, em.getRol());
            ps.setString(9, em.getCargo());
            ps.setString(10, null);
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
    public boolean modificarEmpleado(Empleado em) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE persona SET nombre=?, apellido=?, cedula=?, telefono=?, email=?, usuario=?, contraseña=?, rol=?, cargo=? WHERE idPersona=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNombre());
            ps.setString(2, em.getApellido());
            ps.setString(3, em.getCedula());
            ps.setString(4, em.getTelefono());
            ps.setString(5, em.getEmail());
            ps.setString(6, em.getUsuario());
            ps.setString(7, em.getContrasena());
            ps.setString(8, em.getRol());
            ps.setString(9, em.getCargo());
            ps.setInt(10, em.getId());
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
    public boolean eliminarEmpleado(Empleado em) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM persona WHERE idPersona=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, em.getId());
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
    public List listarEmpleados() {
        ResultSet rs;
        PreparedStatement ps = null;
        List<Empleado> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM persona WHERE rol=\"admin\" OR rol=\"standard\"");
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado();
                em.setId(rs.getInt(1));
                em.setNombre(rs.getString(2));
                em.setApellido(rs.getString(3));
                em.setCedula(rs.getString(4));
                em.setTelefono(rs.getString(5));
                em.setEmail(rs.getString(6));
                em.setUsuario(rs.getString(7));
                em.setContrasena(rs.getString(8));
                em.setRol(rs.getString(9));
                em.setCargo(rs.getString(10));
                datos.add(em);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    public Empleado consultaEmpleado(int idE){
        Empleado emp = new Empleado();
        //Cliente cli = null;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT idPersona, nombre, apellido, cedula, telefono, email, usuario, contraseña, rol, cargo\n"
                    + "FROM Persona\n"
                    + "WHERE idPersona = ? AND (rol = 'admin' OR rol = 'standard')\n"
                    + "LIMIT 1;");
            ps.setInt(1, idE);
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
                // Obtener los datos del resultado y crear un objeto Empleado
                emp.setId(rs.getInt(1));
                emp.setNombre(rs.getString(2));
                emp.setApellido(rs.getString(3));
                emp.setCedula(rs.getString(4));
                emp.setTelefono(rs.getString(5));
                emp.setEmail(rs.getString(6));
                emp.setUsuario(rs.getString(7));
                emp.setContrasena(rs.getString(8));
                emp.setRol(rs.getString(9));
                emp.setCargo(rs.getString(10));
            }else{
                System.out.println("no hay resultados");
            }
        } catch (Exception e) {
        }
        //return datos;
        return emp;
    }
}
