/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Servicio;
import Model.ServicioDTO;
import View.GestionServicios;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author DHANI
 */
public class CtrlServicio extends MouseAdapter implements ActionListener, WindowListener {
    private final Servicio modelo;
    private final ServicioDTO servicioDTO;
    private final GestionServicios vista;
    private DefaultTableModel model = new DefaultTableModel();
    //private DefaultComboBoxModel<String> modelC = new DefaultComboBoxModel<>();
    //private final Validador validador;
    
    public CtrlServicio(Servicio modelo, ServicioDTO servicioDTO, GestionServicios vista){
        this.modelo = modelo;
        this.servicioDTO = servicioDTO;
        this.vista = vista;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
        this.vista.tbServicios.addMouseListener(this);
        this.vista.addWindowListener(this);
        //this.vista.cbVehiculoC.addActionListener(this);
        //Vehiculo modeloVehiculo = new Vehiculo();
        //VehiculoDTO VehiculoDTO = new VehiculoDTO();
    }
    public void iniciar(int id, String placa) {
        vista.setTitle("Servicios");
        vista.setLocationRelativeTo(null);
        //vista.txtId.setVisible(false);
        if(!placa.isEmpty()){
            cargarInformacion(id, placa);
        }

    }
    public void cerrar(){
        System.out.println("cerrando");
        vista.dispose();
    }
    public void limpiar(){
        vista.tfTipo.setText(null);
        vista.tfTiempo.setText(null);
        vista.tfPrecio.setText(null);
        vista.jtDescripcion.setText(null);
        vista.tfIdServicio.setText(null);
        //vista.tfIdVehiculo.setText(null);
        //vista.tfPlaca.setText(null);
    }
    public void cargarInformacion(int id, String placa){
        System.out.println("el id y placa es: " +id+" "+placa);
        vista.tfIdVehiculo.setText(Integer.toString(id));
        vista.tfPlaca.setText(placa);
    }
    public void listar(JTable tbServicios) {
        centrarCeldas(tbServicios);
        model = (DefaultTableModel) tbServicios.getModel();
        tbServicios.setModel(model);
        List<Servicio> lista = servicioDTO.listarServicios(Integer.parseInt(vista.tfIdVehiculo.getText()));
        System.out.println("listar id: "+vista.tfIdVehiculo.getText());
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getTipoServicio();
            objeto[2] = lista.get(i).getTiempoEstimado();
            objeto[3] = lista.get(i).getPrecio();
            objeto[4] = lista.get(i).getDescripcion();
            model.addRow(objeto);
        }
        tbServicios.setRowHeight(35);
        tbServicios.setRowMargin(10);
    }
    void centrarCeldas(JTable tbServicios) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tbServicios.getColumnCount(); i++) {
            tbServicios.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
    void limpiarTabla() {
        for (int i = 0; i < vista.tbServicios.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
    void actualizarElementos(){
        limpiarTabla();
        listar(vista.tbServicios);
    }
    private boolean validarCampos() {
        //return !(vista.tfIdVehiculo.getText().isEmpty() || vista.tfPlaca.getText().isEmpty() || vista.ftfPrecio.getText().isEmpty() || vista.ftfTiempo.getText().isEmpty() || vista.tfTipo.getText().isEmpty());
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
            vista.tfTipo.requestFocus();
            //actualizarElementos();
        }
        if (e.getSource() == vista.btnAgregar) {
            if (validarCampos()) {
                modelo.setTipoServicio(vista.tfTipo.getText());
                try{
                    modelo.setTiempoEstimado(Float.parseFloat(vista.tfTiempo.getText()));
                    modelo.setPrecio(Float.parseFloat(vista.tfPrecio.getText()));
                    modelo.setId_vehiculo(Integer.parseInt(vista.tfIdVehiculo.getText()));
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Error en el valor");
                }
                modelo.setDescripcion(vista.jtDescripcion.getText());
                if (servicioDTO.registrarServicio(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos requeridos");
                if (vista.tfTipo.getText().isEmpty()) {
                    vista.tfTipo.requestFocus();
                } else if (vista.tfTiempo.getText().isEmpty()) {
                    vista.tfTiempo.requestFocus();
                } else if (vista.tfPrecio.getText().isEmpty()) {
                    vista.tfPrecio.requestFocus();
                }else if (vista.jtDescripcion.getText().isEmpty()) {
                    vista.jtDescripcion.requestFocus();
                }
            }
            actualizarElementos();
        }
        if (e.getSource() == vista.btnEliminar) {
            try {
                modelo.setId(Integer.parseInt(vista.tfIdServicio.getText()));
                modelo.setId_vehiculo(Integer.parseInt(vista.tfIdVehiculo.getText()));

                if (servicioDTO.eliminarServicio(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar");
                    limpiar();
                }
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla");
            } finally {
                actualizarElementos();
            }
        }
        if (e.getSource() == vista.btnListar) {
            actualizarElementos();
        }
        if (e.getSource() == vista.btnSalir) {
            vista.dispose();
        }
        
        
    }
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.tbServicios) {
            int filaSeleccionada = vista.tbServicios.getSelectedRow();
            // Obtener valor del campo ID de la fila seleccionada
            String id = vista.tbServicios.getValueAt(filaSeleccionada, 0).toString();
            int indice;
            if (id != null) {
                var sv = servicioDTO.consultarServicio(Integer.parseInt(id));
                //JOptionPane.showMessageDialog(null, "consulta realizada");
                limpiar();
                vista.tfIdServicio.setText(Integer.toString(sv.getId()));
                //vista.tfIdVehiculo.setText(cl.getNombre());
                vista.tfTipo.setText(sv.getTipoServicio());
                vista.tfPrecio.setText(Double.toString(sv.getPrecio()));
                vista.tfTiempo.setText(Double.toString(sv.getTiempoEstimado()));
                vista.jtDescripcion.setText(sv.getDescripcion());
                //var veh = vehiculoDTO.consultaVehiculo(cl.getId_vehiculo());
                //indice = obtenerIndice(veh.getPlaca());
                //vista.cbVehiculoC.setSelectedIndex(indice + 1);
                //vista.cbVehiculoC.setSelectedIndex(cl.getId_vehiculo());
            } else {
                JOptionPane.showMessageDialog(null, "Error al consultar");
                //limpiar();
            }
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Ventana Abierta, Evento aun no implementado"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        // Función que se ejecuta al cargar el JFrame
        //limpiarTabla();
        listar(vista.tbServicios);
        //rellenarCombo(vista.cbVehiculoC);
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
        //new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
