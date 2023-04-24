/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author DHANI
 */
public class Cliente extends Persona{
    private int id_vehiculo;
    private String direccion;

    public Cliente() {
    }

    public Cliente(int id_vehiculo, String direccion, int id, String nombre, String apellido, String cedula, String telefono, String email, String usuario, String contrasena) {
        super(id, nombre, apellido, cedula, telefono, email, usuario, contrasena);
        this.id_vehiculo = id_vehiculo;
        this.direccion = direccion;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the id_vehiculo
     */
    public int getId_vehiculo() {
        return id_vehiculo;
    }

    /**
     * @param id_vehiculo the id_vehiculo to set
     */
    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }
    
}
   