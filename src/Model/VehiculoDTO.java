/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DHANI
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class VehiculoDTO extends Conexion {
    public boolean registrarVehiculo(Vehiculo vh) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sqlCliente = "INSERT INTO vehiculo (placa, tipo, estado, motivo, fechaIngreso, fechaEntrega) VALUES(?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sqlCliente);
            ps.setString(1, vh.getPlaca());
            ps.setString(2, vh.getTipo());
            ps.setString(3, vh.getEstado());
            ps.setString(4, vh.getMotivoIngreso());
            // Convertir java.util.Date a java.sql.Date para fechaIngreso
            java.sql.Date fechaIngresoSql = new java.sql.Date(vh.getFechaIngreso().getTime());
            ps.setDate(5, fechaIngresoSql);
            // Convertir java.util.Date a java.sql.Date para fechaEntrega
            java.sql.Date fechaEntregaSql = new java.sql.Date(vh.getFechaEntrega().getTime());
            ps.setDate(6, fechaEntregaSql);
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

    public boolean modificarVehiculo(Vehiculo vh) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE vehiculo SET placa=?, tipo=?, estado=?, motivo=?, fechaIngreso=?, fechaEntrega=? WHERE idVehiculo=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, vh.getPlaca());
            ps.setString(2, vh.getTipo());
            ps.setString(3, vh.getEstado());
            ps.setString(4, vh.getMotivoIngreso());
            // Convertir java.util.Date a java.sql.Date para fechaIngreso
            java.sql.Date fechaIngresoSql = new java.sql.Date(vh.getFechaIngreso().getTime());
            ps.setDate(5, fechaIngresoSql);
            // Convertir java.util.Date a java.sql.Date para fechaEntrega
            java.sql.Date fechaEntregaSql = new java.sql.Date(vh.getFechaEntrega().getTime());
            ps.setDate(6, fechaEntregaSql);
            ps.setInt(7, vh.getId());
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

    public boolean eliminarVehiculo(Vehiculo vh) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM vehiculo WHERE idVehiculo=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, vh.getId());
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
    
    public List listarVehiculos() {
        ResultSet rs;
        PreparedStatement ps = null;
        List<Vehiculo> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM vehiculo");
            rs = ps.executeQuery();
            while (rs.next()) {
                Vehiculo vh = new Vehiculo();
                vh.setId(rs.getInt(1));
                vh.setPlaca(rs.getString(2));
                vh.setTipo(rs.getString(3));
                vh.setEstado(rs.getString(4));
                vh.setMotivoIngreso(rs.getString(5));
                vh.setFechaIngreso(rs.getDate(6));
                vh.setFechaEntrega(rs.getDate(7));
                datos.add(vh);
            }
        } catch (Exception e) {
        }
        return datos;
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
    java.util.Date convertirFecha(String fechaStr) {
        String formatoFecha = "yyyy/MM/dd"; // Formato de fecha
        java.util.Date fecha = null;
        try {
            // Crear un objeto SimpleDateFormat con el formato de fecha deseado
            SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);

            // Usar el objeto SimpleDateFormat para parsear la cadena de texto a un objeto Date
            fecha = sdf.parse(fechaStr);

            // La fecha se encuentra ahora en un objeto Date
            System.out.println("Fecha: " + fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
    }
    String convertirString(Date fecha) {
        String formatoFecha = "yyyy/MM/dd"; // Formato de fecha
        String fechaStr = null;
        try {
            // Crear un objeto SimpleDateFormat con el formato de fecha deseado
            SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);

            // Usar el objeto SimpleDateFormat para formatear la fecha a una cadena de texto
            fechaStr = sdf.format(fecha);

            // La fecha se encuentra ahora en una cadena de texto con el formato deseado
            System.out.println("Fecha: " + fechaStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fechaStr;
    }

}
