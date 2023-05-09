/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author DHANI
 */
import Model.Cliente;
import Model.ClienteDTO;
import Model.PersonaDTO;
import Model.Persona;
import Model.Vehiculo;
import Model.VehiculoDTO;
import Model.Servicio;
import Model.ServicioDTO;

import View.GestionUnicaUsuario;
import View.Login;
import View.Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class CtrlLogin implements ActionListener {
    private final Persona modelo;
    private final PersonaDTO personaDTO;
    private final Login vista;

    public CtrlLogin(Persona modelo, PersonaDTO personaDTO, Login vista) {
        this.modelo = modelo;
        this.personaDTO = personaDTO;
        this.vista = vista;
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
        this.vista.btnValidar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }
    public void iniciar() {
        vista.setTitle("Login");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        //vista.txtId.setVisible(false);
    }
    public void cerrar(){
        vista.setVisible(false);
    }
    public void limpiar(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
            vista.tfUsername.requestFocus();
        }
        if (e.getSource() == vista.btnValidar) {
            String usuario, contraseña, rol;
            usuario = vista.tfUsername.getText();
            contraseña = vista.tfPassword.getText();
            if(validarCampos()){
                if (personaDTO.validarLogin(usuario, contraseña)) {
                    //JOptionPane.showMessageDialog(null, "Correcto");
                        rol = personaDTO.validarRol(usuario);
                        if (rol == null) {
                            cerrar();
                            Persona modPersona = new Persona();
                            PersonaDTO modP = new PersonaDTO();
                            Cliente modCliente = new Cliente();
                            ClienteDTO modC = new ClienteDTO();
                            Vehiculo modVehiculo = new Vehiculo();
                            VehiculoDTO modV = new VehiculoDTO();
                            Servicio modServicio = new Servicio();
                            ServicioDTO modS = new ServicioDTO();
                            GestionUnicaUsuario principalCliente = new GestionUnicaUsuario();
                            CtrlGestionUnicaUsuario ctrlGestionUnicaUsuario = new CtrlGestionUnicaUsuario(modPersona, modP, modCliente, modC, modVehiculo, modV, modServicio, modS, principalCliente, usuario);
                            //this.vista.dispose();
                            ctrlGestionUnicaUsuario.iniciar();
                            principalCliente.setVisible(true);
                            //principalCliente.tf
                        }else{
                            cerrar();
                            Principal principal = new Principal();
                            principal.setVisible(true);
                            principal.setLocationRelativeTo(null);
                            principal.lblUsuario.setText(usuario.toUpperCase());
                        }
                        //this.dispose();    
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o Contraseña invalida");
                    vista.tfUsername.requestFocus();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Por favor ingrese los datos requeridos");
                vista.tfUsername.requestFocus();
            }
        }
        if (e.getSource() == vista.btnLimpiar) {
            vista.tfUsername.setText(null);
            vista.tfPassword.setText(null);
        }
    }
    public boolean validarCampos(){
        boolean validar = false;
        if(!vista.tfUsername.getText().equals("")){
            validar = true;
        }
        return validar;
    }
    
}
