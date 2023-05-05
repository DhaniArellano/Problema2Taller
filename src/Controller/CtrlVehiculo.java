/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Servicio;
import Model.ServicioDTO;
import Model.VehiculoDTO;
import Model.Vehiculo;
import View.GestionServicios;
import View.GestionVehiculos;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
/**
 *
 * @author DHANI
 */
public class CtrlVehiculo extends MouseAdapter implements ActionListener, WindowListener {

    private final Vehiculo modelo;
    private final VehiculoDTO vehiculoDTO;
    private final GestionVehiculos vista;
    private DefaultTableModel model = new DefaultTableModel();

    public CtrlVehiculo(Vehiculo modelo, VehiculoDTO vehiculoDTO, GestionVehiculos vista) {
        this.modelo = modelo;
        this.vehiculoDTO = vehiculoDTO;
        this.vista = vista;
        this.vista.btnNuevoV.addActionListener(this);
        this.vista.btnGuardarV.addActionListener(this);
        this.vista.btnActualizarV.addActionListener(this);
        this.vista.btnEliminarV.addActionListener(this);
        this.vista.btnListarV.addActionListener(this);
        this.vista.btnSalirV.addActionListener(this);
        this.vista.btnServicio.addActionListener(this);
        this.vista.tbVehiculos.addMouseListener(this);
        this.vista.addWindowListener(this);
    }
    public void iniciar() {
        vista.setTitle("Gestion Vehiculos");
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
        listar(vista.tbVehiculos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (e.getSource() == vista.btnServicio) {
            Servicio modServicio = new Servicio();
            ServicioDTO modS = new ServicioDTO();
            GestionServicios gestionServicios = new GestionServicios();
            CtrlServicio ctrlS = new CtrlServicio(modServicio, modS, gestionServicios);
            int id = Integer.parseInt(vista.tfIdV.getText());
            String placa = vista.tfPlaca.getText();
            ctrlS.iniciar(id, placa);
            //this.dispose();
            gestionServicios.setVisible(true);
            System.out.println("gestionServicios");
        }
        if (e.getSource() == vista.btnNuevoV) {
            limpiar();
            actualizarElementos();
            vista.tfPlaca.requestFocus();
        }
        if (e.getSource() == vista.btnGuardarV) {
            modelo.setPlaca(vista.tfPlaca.getText());
            modelo.setTipo(vista.tfTipo.getText());
            modelo.setEstado(vista.tfEstado.getText());
            modelo.setMotivoIngreso(vista.tfMotivo.getText());
            modelo.setFechaIngreso(convertirFecha(vista.tfIngreso.getText()));
            modelo.setFechaEntrega(convertirFecha(vista.tfEntrega.getText()));
            //comboBox.getSelectedIndex()
            //modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
            //modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
            if(!modelo.getPlaca().equals("")){
                if (vehiculoDTO.registrarVehiculo(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                    //limpiar();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos requeridos");
                vista.tfPlaca.requestFocus();
            }
            actualizarElementos();
        }
        if (e.getSource() == vista.btnActualizarV) {
            modelo.setPlaca(vista.tfPlaca.getText());
            modelo.setTipo(vista.tfTipo.getText());
            modelo.setEstado(vista.tfEstado.getText());
            modelo.setMotivoIngreso(vista.tfMotivo.getText());
            modelo.setFechaIngreso(convertirFecha(vista.tfIngreso.getText()));
            modelo.setFechaEntrega(convertirFecha(vista.tfEntrega.getText()));
            try{
                modelo.setId(Integer.parseInt(vista.tfIdV.getText()));
                if (!vista.tfIdV.getText().equals("")) {
                    if (vehiculoDTO.modificarVehiculo(modelo)) {
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
        if (e.getSource() == vista.btnListarV) {
            //limpiarTabla();
            //listar(vista.tbVehiculos);
            actualizarElementos();
            limpiar();
        }
        if (e.getSource() == vista.btnEliminarV) {
            try{
            modelo.setId(Integer.parseInt(vista.tfIdV.getText()));

            if (vehiculoDTO.eliminarVehiculo(modelo)) {
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
        if (e.getSource() == vista.tbVehiculos) {
            int filaSeleccionada = vista.tbVehiculos.getSelectedRow();
            // Obtener valor del campo ID de la fila seleccionada
            String id = vista.tbVehiculos.getValueAt(filaSeleccionada, 0).toString();
            // Realizar acciones con el valor del ID obtenido
            System.out.println(id);
            //int id = Integer.parseInt((String) vista.tbClientes.getValueAt(fila, 0).toString());
            //String nom = (String) vista.tabla.getValueAt(fila, 1);
            //String correo = (String) vista.tabla.getValueAt(fila, 2);
            //String tel = (String) vista.tabla.getValueAt(fila, 3);
            //vista.txtId.setText("" + id);
            //modelo.setId(clienteDTO.consultaCliente(id).getId());
            
            if (id != null) {
                var vh = vehiculoDTO.consultaVehiculo(Integer.parseInt(id));
                //JOptionPane.showMessageDialog(null, "consulta realizada");
                limpiar();
                vista.tfIdV.setText(Integer.toString(vh.getId()));
                vista.tfPlaca.setText(vh.getPlaca());
                vista.tfTipo.setText(vh.getTipo());
                vista.tfEstado.setText(vh.getEstado());
                vista.tfMotivo.setText(vh.getMotivoIngreso());
                vista.tfIngreso.setText(vh.getFechaIngreso().toString());
                vista.tfEntrega.setText(vh.getFechaEntrega().toString());

            } else {
                JOptionPane.showMessageDialog(null, "Error al consultar");
                //limpiar();
            }
        }
    }
    public void limpiar(){
        vista.tfPlaca.setText(null);
        vista.tfTipo.setText(null);
        vista.tfEstado.setText(null);
        vista.tfMotivo.setText(null);
        vista.tfIngreso.setText("2023-01-01");
        vista.tfEntrega.setText("2023-01-01");
        vista.cbServicio.setSelectedIndex(0);
        vista.tfTurno.setText(null);
        vista.tfIdV.setText(null);
    }
    public void listar(JTable tbVehiculos) {
        centrarCeldas(tbVehiculos);
        model = (DefaultTableModel) tbVehiculos.getModel();
        tbVehiculos.setModel(model);
        List<Vehiculo> lista = vehiculoDTO.listarVehiculos();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getPlaca();
            objeto[2] = lista.get(i).getEstado();
            objeto[3] = lista.get(i).getFechaIngreso();
            objeto[4] = lista.get(i).getFechaEntrega();
            model.addRow(objeto);
        }
        //tbClientes.setRowHeight(35);
        //tbClientes.setRowMargin(10);
    }
    public void actualizarElementos() {
        //limpiar();
        limpiarTabla();
        //limpiarCombo(vista.cbVehiculoC);
        listar(vista.tbVehiculos);
        //rellenarCombo(vista.cbVehiculoC);
    }

    void centrarCeldas(JTable tbClientes) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tbVehiculos.getColumnCount(); i++) {
            tbClientes.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tbVehiculos.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
    Date convertirFecha(String fechaStr){
        String formatoFecha = "yyyy-MM-dd"; // Formato de fecha
        Date fecha = null;
        try {
            // Crear un objeto SimpleDateFormat con el formato de fecha deseado
            SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
            // Usar el objeto SimpleDateFormat para parsear la cadena de texto a un objeto Date
            fecha = sdf.parse(fechaStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
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
