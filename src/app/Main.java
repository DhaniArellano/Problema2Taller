/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import View.Login;
import Controller.CtrlCliente;
import Controller.CtrlVehiculo;
import Controller.CtrlEmpleado;
import Controller.CtrlLogin;
import Model.ClienteDTO;
import Model.Cliente;
import Model.Vehiculo;
import Model.VehiculoDTO;
import Model.Empleado;
import Model.EmpleadoDTO;
import Model.Persona;
import Model.PersonaDTO;
import View.GestionClientes;
import View.GestionVehiculos;
import View.GestionEmpleados;

/**
 *
 * @author DHANI
 */
public class Main {

    public static void main(String[] args) {
        Persona modPersona = new Persona();
        PersonaDTO modP = new PersonaDTO();
        Login login = new Login(); 
        CtrlLogin ctrlL = new CtrlLogin(modPersona, modP, login);
        ctrlL.iniciar();
        login.setVisible(true);
    }
}
