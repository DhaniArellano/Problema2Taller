/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
/**
 *
 * @author DHANI
 */
public class ServicioDTO extends Conexion {
    public boolean registrarServicio(Servicio sv){
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sqlCliente = "INSERT INTO servicios (tipo, descripcion, tiempo, precio, Vehiculo_idVehiculo) VALUES(?,?,?,?,?)";
        try {
            con.setAutoCommit(false); // Iniciar una transacción
            ps = con.prepareStatement(sqlCliente);
            //ps.setInt(1, sv.getId());
            ps.setString(1, sv.getTipoServicio());
            ps.setString(2, sv.getDescripcion());
            ps.setFloat(3, sv.getTiempoEstimado());
            ps.setFloat(4, sv.getPrecio());
            ps.setInt(5, sv.getId_vehiculo());
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
    public boolean eliminarServicio(Servicio ser) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM servicios WHERE idServicios=? AND Vehiculo_idVehiculo =? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ser.getId());
            ps.setInt(2, ser.getId_vehiculo());
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
    public List listarServicios(int id) {
        ResultSet rs;
        PreparedStatement ps = null;
        List<Servicio> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM servicios WHERE Vehiculo_idVehiculo=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Servicio sv = new Servicio();
                sv.setId(rs.getInt(1));
                sv.setTipoServicio(rs.getString(2));
                sv.setDescripcion(rs.getString(3));
                sv.setTiempoEstimado(rs.getFloat(4));
                sv.setPrecio(rs.getFloat(5));
                sv.setId_vehiculo(rs.getInt(6));
                datos.add(sv);
            }
        } catch (Exception e) {
            System.err.println(e);
            //JOptionPane.showMessageDialog(null, "error no hay servicios");
        }
        return datos;
    }
    public Servicio consultarServicio(int id) {
        Servicio ser = new Servicio();
        //Cliente cli = null;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM servicios WHERE idServicios =?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Obtener los datos del resultado y crear un objeto Cliente
                ser.setId(rs.getInt(1));
                ser.setTipoServicio(rs.getString(2));
                ser.setDescripcion(rs.getString(3));
                ser.setTiempoEstimado(rs.getFloat(4));
                ser.setPrecio(rs.getFloat(5));
                ser.setId_vehiculo(rs.getInt(6));
            } else {
                System.out.println("no hay resultados");
            }
        } catch (Exception e) {
        }
        //return datos;
        return ser; 
    }
    public Set<String> obtenerServicios(int id) {
        ResultSet rs;
        PreparedStatement ps = null;
        Set<String> datos = new LinkedHashSet<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM servicios WHERE Vehiculo_idVehiculo = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String placa = rs.getString(2);
                datos.add(placa);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    public int numeroServiciosPorId(int id) {
        int numServicios = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT COUNT(*) AS total_servicios FROM servicios WHERE Vehiculo_idVehiculo=?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                numServicios = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return numServicios;
    }
    public float totalSumaPrecioPorId(int id) {
        float sumaServicios = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT SUM(precio) as total_precio FROM Servicios WHERE Vehiculo_idVehiculo=?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                sumaServicios = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return sumaServicios;
    }
    public float totalSumaTiempoPorId(int id) {
        float sumaTiempo = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT SUM(tiempo) as total_tiempo FROM Servicios WHERE Vehiculo_idVehiculo=?;");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                sumaTiempo = rs.getInt(1);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return sumaTiempo;
    }
    public List<Servicio> listarServiciosTest(int id) {
        System.out.println(id);
        List<Servicio> datos = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Servicio servicio = new Servicio();
            servicio.setId(i);
            servicio.setTipoServicio("servicio " + i);
            servicio.setDescripcion("Descripción " + i);
            servicio.setTiempoEstimado(i * 10);
            servicio.setPrecio(i * 50);
            datos.add(servicio);
        }
        return datos;
    }
    public Servicio consultarServicioTest(int id){
        List<Servicio> datos = new ArrayList<>();
        Servicio servicioEncontrado;
        servicioEncontrado = null;
        for (int i = 1; i <= 5; i++) {
            Servicio servicio = new Servicio();
            servicio.setId(i);
            servicio.setTipoServicio("servicio " + i);
            servicio.setDescripcion("Descripción " + i);
            servicio.setTiempoEstimado(i * 10);
            servicio.setPrecio(i * 50);
            datos.add(servicio);
        }
        for (Servicio servicio : datos) {
            if (servicio.getId() == id) {
                servicioEncontrado = servicio;
            }
        }
        return servicioEncontrado;
    }
    

}
