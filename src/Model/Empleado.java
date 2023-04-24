/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DHANI
 */
public class Empleado extends Persona {
    private String cargo;
    //private List<Turno> turnos;
    //private int turno;
    private String rol;

    public Empleado() {
    }

    public Empleado(String cargo, String rol, int id, String nombre, String apellido, String cedula, String telefono, String email, String usuario, String contrasena) {
        super(id, nombre, apellido, cedula, telefono, email, usuario, contrasena);
        this.cargo = cargo;
        this.rol = rol;
    }

    

    

    
   
    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
