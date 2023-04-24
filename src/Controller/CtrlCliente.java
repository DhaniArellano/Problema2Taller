/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ClienteDTO;
import Model.Cliente;
import View.GestionClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Set;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author DHANI
 */
public class CtrlCliente extends MouseAdapter implements ActionListener, WindowListener {
    private final Cliente modelo;
    private final ClienteDTO clienteDTO;
    private final GestionClientes vista;
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultComboBoxModel<String> modelC = new DefaultComboBoxModel<>();

    public CtrlCliente(Cliente modelo, ClienteDTO clienteDTO, GestionClientes vista) {
        this.modelo = modelo;
        this.clienteDTO = clienteDTO;
        this.vista = vista;
        this.vista.btnNuevoC.addActionListener(this);
        this.vista.btnGuardarC.addActionListener(this);
        this.vista.btnActualizarC.addActionListener(this);
        this.vista.btnEliminarC.addActionListener(this);
        this.vista.btnListarC.addActionListener(this);
        this.vista.btnSalirC.addActionListener(this);
        this.vista.tbClientes.addMouseListener(this);
        this.vista.addWindowListener(this);
        this.vista.cbVehiculoC.addActionListener(this);
    }
    public void iniciar() {
        vista.setTitle("Gestion Clientes");
        vista.setLocationRelativeTo(null);
        //vista.txtId.setVisible(false);
        
    }
    public void cerrar(){
        System.out.println("cerrar");
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // Función que se ejecuta al cargar el JFrame
        limpiarTabla();
        listar(vista.tbClientes);
        rellenarCombo(vista.cbVehiculoC);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (e.getSource() == vista.btnNuevoC) {
            limpiar();
            vista.tfNombresC.requestFocus();
            actualizarElementos();
        }
        if (e.getSource() == vista.btnGuardarC) {
            modelo.setNombre(vista.tfNombresC.getText());
            modelo.setApellido(vista.tfApellidosC.getText());
            modelo.setDireccion(vista.tfDireccionC.getText());
            modelo.setCedula(vista.tfCedulaC.getText());
            modelo.setTelefono(vista.tfTelefonoC.getText());
            modelo.setEmail(vista.tfEmailC.getText());
            //System.out.println(vista.cbVehiculoC.getSelectedIndex());
            modelo.setId_vehiculo(vista.cbVehiculoC.getSelectedIndex());
            modelo.setUsuario(vista.tfUsuarioC.getText());
            modelo.setContrasena(vista.tfContraC.getText());
            //comboBox.getSelectedIndex()
            //modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
            //modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
            if(!modelo.getCedula().equals("")){
                if (clienteDTO.registrarCliente(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                    //limpiar();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos requeridos");
            }
            actualizarElementos();
        }
        if (e.getSource() == vista.btnActualizarC) {
            modelo.setNombre(vista.tfNombresC.getText());
            modelo.setApellido(vista.tfApellidosC.getText());
            modelo.setDireccion(vista.tfDireccionC.getText());
            modelo.setCedula(vista.tfCedulaC.getText());
            modelo.setTelefono(vista.tfTelefonoC.getText());
            modelo.setEmail(vista.tfEmailC.getText());
            modelo.setId_vehiculo((vista.cbVehiculoC.getSelectedIndex()));
            modelo.setUsuario(vista.tfUsuarioC.getText());
            modelo.setContrasena(vista.tfContraC.getText());
            try{
                modelo.setId(Integer.parseInt(vista.tfIdC.getText()));
                if (!vista.tfIdC.getText().equals("")) {
                    if (clienteDTO.modificarCliente(modelo)) {
                        JOptionPane.showMessageDialog(null, "Registro Modificado");
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Modificar");
                        limpiar();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla");
                }

            }catch(Exception er){
                System.err.println(er);
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla");
            }finally{
                actualizarElementos();
            }
        }
        if (e.getSource() == vista.btnListarC) {
            //limpiarTabla();
            //listar(vista.tbClientes);
            //limpiar();
            actualizarElementos();
        }
        if (e.getSource() == vista.btnEliminarC) {
            try{
            modelo.setId(Integer.parseInt(vista.tfIdC.getText()));

            if (clienteDTO.eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
            }catch(Exception err){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla");
            }finally{
                actualizarElementos();
            }
        }

    }
    public void mouseClicked(MouseEvent e) {
        // Manejar eventos de clic en la tabla
        if (e.getSource() == vista.tbClientes) {
            int filaSeleccionada = vista.tbClientes.getSelectedRow();
            // Obtener valor del campo ID de la fila seleccionada
            String id = vista.tbClientes.getValueAt(filaSeleccionada, 0).toString();
            // Realizar acciones con el valor del ID obtenido
            //System.out.println(id);
            //int id = Integer.parseInt((String) vista.tbClientes.getValueAt(fila, 0).toString());
            //String nom = (String) vista.tabla.getValueAt(fila, 1);
            //String correo = (String) vista.tabla.getValueAt(fila, 2);
            //String tel = (String) vista.tabla.getValueAt(fila, 3);
            //vista.txtId.setText("" + id);
            //modelo.setId(clienteDTO.consultaCliente(id).getId());
            
            if (id != null) {
                var cl = clienteDTO.consultaCliente(Integer.parseInt(id));
                //JOptionPane.showMessageDialog(null, "consulta realizada");
                limpiar();
                vista.tfIdC.setText(Integer.toString(cl.getId()));
                vista.tfNombresC.setText(cl.getNombre());
                vista.tfApellidosC.setText(cl.getApellido());
                vista.tfDireccionC.setText(cl.getDireccion());
                vista.tfCedulaC.setText(cl.getCedula());
                vista.tfTelefonoC.setText(cl.getTelefono());
                vista.tfEmailC.setText(cl.getEmail());
                System.out.println(cl.getId_vehiculo());
                vista.cbVehiculoC.setSelectedIndex(cl.getId_vehiculo());
                vista.tfUsuarioC.setText(cl.getUsuario());
                vista.tfContraC.setText(cl.getContrasena());
            } else {
                JOptionPane.showMessageDialog(null, "Error al consultar");
                //limpiar();
            }
        }
    }
    public void actualizarElementos(){
        //limpiar();
        limpiarTabla();
        limpiarCombo(vista.cbVehiculoC);
        listar(vista.tbClientes);
        rellenarCombo(vista.cbVehiculoC);
    }
    public void limpiar(){
        vista.tfNombresC.setText(null);
        vista.tfApellidosC.setText(null);
        vista.tfDireccionC.setText(null);
        vista.tfCedulaC.setText(null);
        vista.tfTelefonoC.setText(null);
        vista.tfEmailC.setText(null);
        vista.cbVehiculoC.setSelectedIndex(0);
        vista.tfUsuarioC.setText(null);
        vista.tfContraC.setText(null);
        vista.tfIdC.setText(null);
    }
    public void listar(JTable tbClientes) {
        centrarCeldas(tbClientes);
        model = (DefaultTableModel) tbClientes.getModel();
        tbClientes.setModel(model);
        List<Cliente> lista = clienteDTO.listarClientes();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getApellido();
            objeto[3] = lista.get(i).getCedula();
            objeto[4] = lista.get(i).getTelefono();
            model.addRow(objeto);
        }
        //tbClientes.setRowHeight(35);
        //tbClientes.setRowMargin(10);
    }
    public void limpiarCombo(JComboBox cbVehiculoC){
        cbVehiculoC.removeAllItems();
    }
    public void rellenarCombo(JComboBox cbVehiculoC) {
        Set<String> datos = clienteDTO.obtenerPlacas();
        //modelC = (DefaultComboBoxModel) cbVehiculoC.getModel();
        cbVehiculoC.addItem("");
        //modelC.addAll(datos);
        for (String placa : datos){
            cbVehiculoC.addItem(placa);
        }
        //cbVehiculoC.setSelectedIndex(-1);
        //cbVehiculoC.setModel(modelC);
    }

    void centrarCeldas(JTable tbClientes) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tbClientes.getColumnCount(); i++) {
            tbClientes.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tbClientes.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
