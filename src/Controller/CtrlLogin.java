/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author DHANI
 */
import Model.PersonaDTO;
import Model.Persona;
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
            if(personaDTO.validarLogin(usuario, contraseña)){
                //JOptionPane.showMessageDialog(null, "Correcto");
                cerrar();
                Principal principal = new Principal();
                principal.setVisible(true);
                principal.setLocationRelativeTo(null);
                principal.lblUsuario.setText(usuario.toUpperCase());
                try{
                    rol = personaDTO.validarRol(usuario);
                    if (rol.equals("admin") || rol.equals("standard")) {
                        principal.btnReportes.setEnabled(true);
                    } else {
                        System.out.println("es un cliente");
                    }
                    //this.dispose();    
                }catch(Exception ex){
                    
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Usuario o Contraseña invalida");
                vista.tfUsername.requestFocus();
            }
        }
        if (e.getSource() == vista.btnLimpiar) {
            vista.tfUsername.setText(null);
            vista.tfPassword.setText(null);
        }
    }
    
}
