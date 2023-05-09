/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.PersonaDTO;
import Model.Persona;
import Model.Cliente;
import Model.ClienteDTO;
import Model.Servicio;
import Model.ServicioDTO;
import Model.Vehiculo;
import Model.VehiculoDTO;

import View.GestionUnicaUsuario;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author DHANI
 */
public class CtrlGestionUnicaUsuario implements ActionListener {
    private final Persona modeloPersona;
    private final PersonaDTO personaDTO;
    private final Cliente modeloCliente;
    private final ClienteDTO clienteDTO;
    private final Vehiculo modeloVehiculo;
    private final VehiculoDTO vehiculoDTO;
    private final Servicio modeloServicio;
    private final ServicioDTO servicioDTO;
    private final GestionUnicaUsuario vista;
    private DefaultTableModel model = new DefaultTableModel();
    private String username;

    public CtrlGestionUnicaUsuario(Persona modeloPersona, PersonaDTO personaDTO, Cliente modeloCliente, ClienteDTO clienteDTO, Vehiculo modeloVehiculo, VehiculoDTO vehiculoDTO, Servicio modeloServicio, ServicioDTO servicioDTO, GestionUnicaUsuario vista, String username) {
        this.modeloPersona = modeloPersona;
        this.personaDTO = personaDTO;
        this.modeloCliente = modeloCliente;
        this.clienteDTO = clienteDTO;
        this.modeloVehiculo = modeloVehiculo;
        this.vehiculoDTO = vehiculoDTO;
        this.modeloServicio = modeloServicio;
        this.servicioDTO = servicioDTO;
        this.vista = vista;
        this.username = username;
        this.vista.btnSalir.addActionListener(this);
    }

    
    
    public void iniciar() {
        vista.setTitle("Gestion Unica de Usuario");
        vista.setLocationRelativeTo(null);
        try{
            int id = personaDTO.devolverId(username);
            var cliente = clienteDTO.consultaCliente(id);
            var vehiculo = vehiculoDTO.consultaVehiculo(cliente.getId_vehiculo());
            //persona
            vista.tfNombres.setText(cliente.getNombre());
            vista.tfApellidos.setText(cliente.getApellido());
            vista.tfDireccion.setText(cliente.getDireccion());
            vista.tfCedula.setText(cliente.getCedula());
            vista.tfTelefono.setText(cliente.getTelefono());
            vista.tfTelefono.setText(cliente.getTelefono());
            vista.tfEmail.setText(cliente.getEmail());
            vista.tfIdCliente.setText(Integer.toString(id));
            //vehiculo
            vista.tfPlaca.setText(vehiculo.getPlaca());
            vista.tfTipo.setText(vehiculo.getTipo());
            vista.tfEstado.setText(vehiculo.getEstado());
            vista.tfIngreso.setText(vehiculo.getFechaIngreso().toString());
            vista.tfEntrega.setText(vehiculo.getFechaEntrega().toString());
            vista.tfTurno.setText("0");
            vista.tfIdVehiculo.setText(Integer.toString(cliente.getId_vehiculo()));
            //servicios
            listar(vista.tbServicios);
            vista.tfNumServ.setText(Integer.toString(servicioDTO.numeroServiciosPorId(cliente.getId_vehiculo())));
            vista.tfTiempoEst.setText(Float.toString(servicioDTO.totalSumaTiempoPorId(cliente.getId_vehiculo())) + " min");
            vista.tfTotal.setText("$ " + Float.toString(servicioDTO.totalSumaPrecioPorId(cliente.getId_vehiculo())));
            //vista.txtId.setVisible(false);
        }catch(Exception ex){
            System.err.println(ex.toString());
        }
    }
    public void cerrar() {
        Persona modPersona = new Persona();
        PersonaDTO modP = new PersonaDTO();
        Login login = new Login();
        //login.setVisible(true);
        //login.setLocationRelativeTo(null);
        CtrlLogin ctrlL = new CtrlLogin(modPersona, modP, login);
        this.vista.dispose();
        ctrlL.iniciar();
        login.setVisible(true);
    }
    void limpiarTabla() {
        for (int i = 0; i < vista.tbServicios.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
    void centrarCeldas(JTable tbServicios) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tbServicios.getColumnCount(); i++) {
            tbServicios.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
    public void listar(JTable tbServicios) {
        centrarCeldas(tbServicios);
        model = (DefaultTableModel) tbServicios.getModel();
        tbServicios.setModel(model);
        List<Servicio> lista = servicioDTO.listarServicios(Integer.parseInt(vista.tfIdVehiculo.getText()));
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getTipoServicio();
            objeto[2] = lista.get(i).getTiempoEstimado();
            objeto[3] = lista.get(i).getPrecio();
            objeto[4] = lista.get(i).getDescripcion();
            model.addRow(objeto);
        }
        tbServicios.setRowHeight(25);
        tbServicios.setRowMargin(10);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (e.getSource() == vista.btnSalir) {
            cerrar();
        }
    }
    
}
