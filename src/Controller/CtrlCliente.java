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
import java.util.ArrayList;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import Utils.Validador;
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
    private final Validador validador;

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
        this.validador = new Validador();
        
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
            String placa;
            int id;
            id = 0; 
            placa = vista.cbVehiculoC.getSelectedItem().toString();
            if (placa != null || !placa.equals("")) {
                id = clienteDTO.verificarAuto(placa);
            }
            if (validarCampos() && !verificarVehiculoAsociado(id)) {
                modelo.setNombre(vista.tfNombresC.getText());
                modelo.setApellido(vista.tfApellidosC.getText());
                modelo.setDireccion(vista.tfDireccionC.getText());
                modelo.setCedula(vista.tfCedulaC.getText());
                modelo.setTelefono(vista.tfTelefonoC.getText());
                modelo.setUsuario(vista.tfUsuarioC.getText());
                modelo.setContrasena(vista.tfContraC.getText());
                modelo.setEmail(vista.tfEmailC.getText());
                //System.out.println("item: "+vista.cbVehiculoC.getSelectedItem().toString());
                //System.out.println("index: "+vista.cbVehiculoC.getSelectedIndex());
                modelo.setId_vehiculo(id);
                if (clienteDTO.registrarCliente(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos requeridos");
                if (vista.tfNombresC.getText().isEmpty()) {
                    vista.tfNombresC.requestFocus();
                } else if (vista.tfApellidosC.getText().isEmpty()) {
                    vista.tfApellidosC.requestFocus();
                } else if (vista.tfDireccionC.getText().isEmpty()) {
                    vista.tfDireccionC.requestFocus();
                } else if (vista.tfCedulaC.getText().isEmpty()) {
                    vista.tfCedulaC.requestFocus();
                } else if (!validador.validarTelefono(vista.tfTelefonoC.getText())) {
                    JOptionPane.showMessageDialog(null, "Telefono en formato incorrecto (10 Números)");
                    vista.tfTelefonoC.requestFocus();
                } else if (!Validador.validarEmail(vista.tfEmailC.getText())) {
                    JOptionPane.showMessageDialog(null, "Email en formato incorrecto");
                    vista.tfEmailC.requestFocus();
                } else if (vista.cbVehiculoC.getSelectedItem().toString().equals("-1")||vista.cbVehiculoC.getSelectedItem().toString().equals("")||vista.cbVehiculoC.getSelectedItem().toString() == null){
                    JOptionPane.showMessageDialog(null, "Seleccione un vehículo");
                    vista.cbVehiculoC.requestFocus();
                } else if (vista.tfUsuarioC.getText().isEmpty()) {
                    vista.tfUsuarioC.requestFocus();
                } else if (vista.tfContraC.getText().isEmpty()) {
                    vista.tfContraC.requestFocus();
                }
            }
            actualizarElementos();
        }
        if (e.getSource() == vista.btnActualizarC) {
            String placa="";
            int id ;
            int idCliente = 0;
            int idVehiculo = 0;
            try{
                placa = vista.cbVehiculoC.getSelectedItem().toString();
                if (placa != null || !placa.equals("")) {
                    idVehiculo = clienteDTO.verificarAuto(placa);
                    idCliente = Integer.parseInt(vista.tfIdC.getText());
                    System.out.println("placa: "+placa+" idVehiculo:"+idVehiculo);
                }
            }catch(Exception exc){
                System.err.println(exc);
            }
            if (validarCampos() && validarActualizacion(idCliente, idVehiculo)) {
                modelo.setNombre(vista.tfNombresC.getText());
                modelo.setApellido(vista.tfApellidosC.getText());
                modelo.setDireccion(vista.tfDireccionC.getText());
                modelo.setCedula(vista.tfCedulaC.getText());
                modelo.setTelefono(vista.tfTelefonoC.getText());
                modelo.setEmail(vista.tfEmailC.getText());
                
                modelo.setUsuario(vista.tfUsuarioC.getText());
                modelo.setContrasena(vista.tfContraC.getText());
                placa = vista.cbVehiculoC.getSelectedItem().toString();
                if (placa != null || !placa.equals("")) {
                    id = clienteDTO.verificarAuto(placa);
                    idCliente = Integer.parseInt(vista.tfIdC.getText());
                    modelo.setId_vehiculo(id);
                }
                try {
                    modelo.setId(Integer.parseInt(vista.tfIdC.getText()));
                    if (!vista.tfIdC.getText().equals("")) {
                        if (clienteDTO.modificarCliente(modelo)) {
                            JOptionPane.showMessageDialog(null, "Registro Modificado");
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al Modificar");
                            limpiar();
                        }
                    } else {
                        System.out.println("no se selecciono un registro");
                    }

                } catch (Exception er) {
                    System.err.println(er);
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla");
                } finally {
                    actualizarElementos();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos requeridos");
                if (vista.tfIdC.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla");
                }
                if (vista.tfNombresC.getText().isEmpty()) {
                    vista.tfNombresC.requestFocus();
                } else if (vista.tfApellidosC.getText().isEmpty()) {
                    vista.tfApellidosC.requestFocus();
                } else if (vista.tfDireccionC.getText().isEmpty()) {
                    vista.tfDireccionC.requestFocus();
                } else if (vista.tfCedulaC.getText().isEmpty()) {
                    vista.tfCedulaC.requestFocus();
                } else if (!validador.validarTelefono(vista.tfTelefonoC.getText())) {
                    JOptionPane.showMessageDialog(null, "Telefono en formato incorrecto (10 Números)");
                    vista.tfTelefonoC.requestFocus();
                } else if (!Validador.validarEmail(vista.tfEmailC.getText())) {
                    JOptionPane.showMessageDialog(null, "Email en formato incorrecto");
                    vista.tfEmailC.requestFocus();
                } else if (vista.cbVehiculoC.getSelectedItem().toString().equals("-1") || vista.cbVehiculoC.getSelectedItem().toString().equals("") || vista.cbVehiculoC.getSelectedItem().toString() == null) {
                    JOptionPane.showMessageDialog(null, "Seleccione un vehículo");
                    vista.cbVehiculoC.requestFocus();
                } else if (vista.tfUsuarioC.getText().isEmpty()) {
                    vista.tfUsuarioC.requestFocus();
                } else if (vista.tfContraC.getText().isEmpty()) {
                    vista.tfContraC.requestFocus();
                }
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
                JOptionPane.showMessageDialog(null, "Error al Eliminar, existe una referencia con otra entidad");
                limpiar();
            }
            }catch(Exception err){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla");
            }finally{
                actualizarElementos();
            }
        }

    }
    public boolean verificarVehiculoAsociado(int id){
        boolean resultado;
        resultado = false;
        try{
            resultado = clienteDTO.verificarVehiculoAsociado(id);
            System.out.println("resultado vehiculo asociado: "+resultado);
            if (resultado) {
                JOptionPane.showMessageDialog(null, "El vehiculo ya está asociado a otro cliente");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "error consultado si el vehiculo ya está asociado a otra persona");
        }
        return resultado;
    }
    public boolean validarActualizacion(int idVehiculo, int idCliente){
        boolean resultado = false;
        if(clienteDTO.validarActualizacion(idVehiculo, idCliente)){
            resultado = true;
        }else{
            JOptionPane.showMessageDialog(null, "El vehículo ya está asociado a otro cliente");
        }
        return resultado;
    }
    public void mouseClicked(MouseEvent e) {
        // Manejar eventos de clic en la tabla
        if (e.getSource() == vista.tbClientes) {
            int filaSeleccionada = vista.tbClientes.getSelectedRow();
            // Obtener valor del campo ID de la fila seleccionada
            String id = vista.tbClientes.getValueAt(filaSeleccionada, 0).toString();
            int indice;
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
                var veh = clienteDTO.consultaVehiculo(cl.getId_vehiculo());
                indice = obtenerIndice(veh.getPlaca());
                vista.cbVehiculoC.setSelectedIndex(indice+1);
                //vista.cbVehiculoC.setSelectedIndex(cl.getId_vehiculo());
                //vista.cbVehiculoC.setSelectedItem("fds");
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
        tbClientes.setRowHeight(25);
        tbClientes.setRowMargin(10);
    }
    public int verificarAuto(String placa){
        int veh=0;
        veh = clienteDTO.verificarAuto(placa);
        return veh;
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
    public int obtenerIndice(String placaBuscada){
            Set<String> datos = clienteDTO.obtenerPlacas();
            int indice=0;
            if (datos.contains(placaBuscada)) {
                indice = new ArrayList<>(datos).indexOf(placaBuscada);
                System.out.println("placa encontrada");
            }else{
                System.out.println("no se encontro placa");
            }
            return indice;
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
    private boolean validarCampos() {
        boolean resultado;
        resultado = !(vista.tfCedulaC.getText().isEmpty() || vista.tfNombresC.getText().isEmpty() || vista.tfApellidosC.getText().isEmpty() || vista.tfDireccionC.getText().isEmpty() || !validador.validarEmail(vista.tfEmailC.getText()) || vista.tfUsuarioC.getText().isEmpty() || vista.tfContraC.getText().isEmpty() || vista.cbVehiculoC.getSelectedIndex()==0 || vista.cbVehiculoC.getSelectedItem().toString() == null || !validador.validarTelefono(vista.tfTelefonoC.getText()));
        System.out.println("validar campos: "+resultado);
        return resultado;
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
