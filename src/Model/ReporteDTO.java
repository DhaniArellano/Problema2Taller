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
/**
 *
 * @author DHANI
 */
public class ReporteDTO extends Conexion{
    public int numeroServicios(){
        int numServicios = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT COUNT(*) AS total_servicios FROM servicios;");
            rs = ps.executeQuery();
            if (rs.next()) {
                numServicios = rs.getInt(1);
            }
        }catch(Exception ex){
            System.err.println(ex);
        }
        return numServicios;
    }
    public int vehiculosAtendidos(){
        int numVehiculos = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT COUNT(DISTINCT Vehiculo_idVehiculo) FROM servicios");
            rs = ps.executeQuery();
            if (rs.next()) {
                numVehiculos = rs.getInt(1);
            }
        }catch(Exception ex){
            System.err.println(ex);
        }
        return numVehiculos;
    }
    public float totalSumaPrecio(){
        float sumaServicios = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT SUM(precio) as total_precio FROM Servicios;");
            rs = ps.executeQuery();
            if (rs.next()) {
                sumaServicios = rs.getInt(1);
            }
        }catch(Exception ex){
            System.err.println(ex);
        }
        return sumaServicios;
    }
    public float totalSumaTiempo(){
        float sumaTiempo = 0;
        ResultSet rs;
        PreparedStatement ps = null;
        //List<Cliente> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT SUM(tiempo) as total_tiempo FROM Servicios;");
            rs = ps.executeQuery();
            if (rs.next()) {
                sumaTiempo = rs.getInt(1);
            }
        }catch(Exception ex){
            System.err.println(ex);
        }
        return sumaTiempo;
    }
    public List listarInformacion(){
        ResultSet rs;
        PreparedStatement ps = null;
        ArrayList<Object[]> resultados = new ArrayList<Object[]>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT v.placa AS 'Placa del Vehiculo', COUNT(*) AS 'Numero de servicios', SUM(s.tiempo) AS 'Tiempo Total', SUM(s.precio) AS 'Precio Total'\n"
                    + "FROM vehiculo v\n"
                    + "LEFT JOIN servicios s ON v.idVehiculo = s.Vehiculo_idVehiculo\n"
                    + "GROUP BY v.placa\n"
                    + "ORDER BY v.placa ASC;");
            rs = ps.executeQuery();
            // Recorrer el resultado por fila y guardar los valores en un arreglo de objetos
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("Placa del Vehiculo");
                fila[1] = rs.getInt("Numero de servicios");
                fila[2] = rs.getInt("Tiempo Total");
                fila[3] = rs.getDouble("Precio Total");
                resultados.add(fila);
            }
        }catch(SQLException exc){
            System.err.println(exc);
        }
        return resultados;
    }
}
