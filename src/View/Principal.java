/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.CtrlCliente;
import Controller.CtrlEmpleado;
import Controller.CtrlLogin;
import Controller.CtrlVehiculo;
import Model.Cliente;
import Model.ClienteDTO;
import Model.Empleado;
import Model.EmpleadoDTO;
import Model.Persona;
import Model.PersonaDTO;
import Model.Vehiculo;
import Model.VehiculoDTO;

/**
 *
 * @author DHANI
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnGestionClientes = new javax.swing.JButton();
        btnGestionVehiculos = new javax.swing.JButton();
        btnGestionEmpleados = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido, username!");

        btnGestionClientes.setText("Gestion Clientes");
        btnGestionClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionClientesActionPerformed(evt);
            }
        });

        btnGestionVehiculos.setText("Gestion Vehiculos");
        btnGestionVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionVehiculosActionPerformed(evt);
            }
        });

        btnGestionEmpleados.setText("Gestion Empleados");
        btnGestionEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionEmpleadosActionPerformed(evt);
            }
        });

        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnReportes.setText("Reportes");
        btnReportes.setEnabled(false);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGestionClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGestionVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGestionEmpleados)
                            .addComponent(btnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(134, 134, 134))))
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(65, 65, 65)
                .addComponent(btnGestionClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGestionVehiculos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGestionEmpleados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReportes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGestionClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionClientesActionPerformed
        // TODO add your handling code here:
        /*
        GestionClientes gestionClientes = new GestionClientes();
        gestionClientes.setVisible(true);
        this.dispose();
        gestionClientes.setLocationRelativeTo(null);
        */
        Cliente modCliente = new Cliente();
        ClienteDTO modC = new ClienteDTO();
        GestionClientes gestionClientes = new GestionClientes();
        CtrlCliente ctrl = new CtrlCliente(modCliente, modC, gestionClientes);
        this.dispose();
        ctrl.iniciar();
        gestionClientes.setVisible(true);
    }//GEN-LAST:event_btnGestionClientesActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        Persona modPersona = new Persona();
        PersonaDTO modP = new PersonaDTO();
        Login login = new Login();
        //login.setVisible(true);
        //login.setLocationRelativeTo(null);
        CtrlLogin ctrlL = new CtrlLogin(modPersona, modP, login);
        this.dispose();
        ctrlL.iniciar();
        login.setVisible(true);
        /*
        Login login = new Login();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        */
        
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnGestionVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionVehiculosActionPerformed
        // TODO add your handling code here:
        /*
        GestionVehiculos gestionVehiculos = new GestionVehiculos();
        gestionVehiculos.setVisible(true);
        this.dispose();
        gestionVehiculos.setLocationRelativeTo(null);
        */
        Vehiculo modVehiculo = new Vehiculo();
        VehiculoDTO modV = new VehiculoDTO();

        GestionVehiculos gestionVehiculos = new GestionVehiculos();
        CtrlVehiculo ctrlV = new CtrlVehiculo(modVehiculo, modV, gestionVehiculos);
        ctrlV.iniciar();
        this.dispose();
        gestionVehiculos.setVisible(true);
    }//GEN-LAST:event_btnGestionVehiculosActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
        ReportesFrame reportesFrame = new ReportesFrame();
        reportesFrame.setVisible(true);
        this.dispose();
        reportesFrame.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnGestionEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionEmpleadosActionPerformed
        // TODO add your handling code here:
        /*
        GestionEmpleados gestionEmpleados = new GestionEmpleados();
        gestionEmpleados.setVisible(true);
        this.dispose();
        gestionEmpleados.setLocationRelativeTo(null);
        */
        Empleado modEmpleado = new Empleado();
        EmpleadoDTO modE = new EmpleadoDTO();
        GestionEmpleados gestionEmpleados = new GestionEmpleados();

        CtrlEmpleado ctrlE = new CtrlEmpleado(modEmpleado, modE, gestionEmpleados);
        ctrlE.iniciar();
        gestionEmpleados.setVisible(true);
    }//GEN-LAST:event_btnGestionEmpleadosActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCerrarSesion;
    public javax.swing.JButton btnGestionClientes;
    public javax.swing.JButton btnGestionEmpleados;
    public javax.swing.JButton btnGestionVehiculos;
    public javax.swing.JButton btnReportes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jpPrincipal;
    // End of variables declaration//GEN-END:variables
}
