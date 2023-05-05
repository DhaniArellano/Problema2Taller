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
import java.util.List;
/**
 *
 * @author DHANI
 */
public class ServicioDTO extends Conexion {
    public List listarServiciosALT(int id) {
        ResultSet rs;
        PreparedStatement ps = null;
        List<Servicio> datos = new ArrayList<>();
        try {
            Connection con = getConexion();
            ps = con.prepareStatement("SELECT * FROM servicios WHERE idVehiculo=?");
            rs = ps.executeQuery();
            while (rs.next()) {
                /*     
                private int id;
                private String tipoServicio;
                private String descripcion;
                private int tiempoEstimado; // En minutos
                private double precio;  
                */
                Servicio sv = new Servicio();
                sv.setId(rs.getInt(1));
                sv.setTipoServicio(rs.getString(2));
                sv.setDescripcion(rs.getString(3));
                sv.setTiempoEstimado(rs.getInt(4));
                sv.setPrecio(rs.getDouble(5));
                datos.add(sv);
            }
        } catch (Exception e) {
        }
        return datos;
    }
    public List<Servicio> listarServicios(int id) {
        System.out.println(id);
        List<Servicio> datos = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Servicio servicio = new Servicio();
            servicio.setId(i);
            servicio.setTipoServicio("servicio " + i);
            servicio.setDescripcion("Descripción " + i);
            servicio.setTiempoEstimado(i * 10);
            servicio.setPrecio(i * 50.0);
            datos.add(servicio);
        }
        return datos;
    }
    public Servicio consultarServicio(int id){
        List<Servicio> datos = new ArrayList<>();
        Servicio servicioEncontrado;
        servicioEncontrado = null;
        for (int i = 1; i <= 5; i++) {
            Servicio servicio = new Servicio();
            servicio.setId(i);
            servicio.setTipoServicio("servicio " + i);
            servicio.setDescripcion("Descripción " + i);
            servicio.setTiempoEstimado(i * 10);
            servicio.setPrecio(i * 50.0);
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
