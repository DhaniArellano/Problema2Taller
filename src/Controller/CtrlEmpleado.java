/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.EmpleadoDTO;
import Model.Empleado;
import View.GestionEmpleados;
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
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author DHANI
 */
public class CtrlEmpleado extends MouseAdapter implements ActionListener, WindowListener {
    private final Empleado modelo;
    private final EmpleadoDTO empleadoDTO;
    private final GestionEmpleados vista;
    private DefaultTableModel model = new DefaultTableModel();

    public CtrlEmpleado(Empleado modelo, EmpleadoDTO empleadoDTO, GestionEmpleados vista) {
        this.modelo = modelo;
        this.empleadoDTO = empleadoDTO;
        this.vista = vista;
        this.vista.btnNuevoE.addActionListener(this);
        this.vista.btnGuardarE.addActionListener(this);
        this.vista.btnActualizarE.addActionListener(this);
        this.vista.btnEliminarE.addActionListener(this);
        this.vista.btnListarE.addActionListener(this);
        this.vista.btnSalirE.addActionListener(this);
        this.vista.tbEmpleados.addMouseListener(this);
        this.vista.addWindowListener(this);
    }
    public void iniciar() {
        vista.setTitle("Gestion Empleados");
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
        listar(vista.tbEmpleados);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (e.getSource() == vista.btnNuevoE) {
            limpiar();
            vista.tfNombresE.requestFocus();
        }
        if (e.getSource() == vista.btnGuardarE) {
            modelo.setNombre(vista.tfNombresE.getText());
            modelo.setApellido(vista.tfApellidosE.getText());
            //modelo.setDireccion(vista.tfDireccionC.getText());
            modelo.setCedula(vista.tfCedulaE.getText());
            modelo.setTelefono(vista.tfCedulaE.getText());
            modelo.setEmail(vista.tfEmailE.getText());
            modelo.setCargo(vista.tfCargoE.getText());
            //modelo.setId_vehiculo((vista.cbVehiculoC.getSelectedIndex() + 1));
            modelo.setUsuario(vista.tfUsuarioE.getText());
            modelo.setContrasena(vista.tfContraE.getText());
            modelo.setRol((String) vista.cbRolE.getSelectedItem());
            //comboBox.getSelectedIndex()
            //modelo.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
            //modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
            if (!modelo.getCedula().equals("")) {
                if (empleadoDTO.registrarEmpleado(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                    //limpiar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos requeridos");
            }

        }
        if (e.getSource() == vista.btnActualizarE) {
            modelo.setNombre(vista.tfNombresE.getText());
            modelo.setApellido(vista.tfApellidosE.getText());
            //modelo.setDireccion(vista.tfDireccionC.getText());
            modelo.setCedula(vista.tfCedulaE.getText());
            modelo.setTelefono(vista.tfCedulaE.getText());
            modelo.setEmail(vista.tfEmailE.getText());
            modelo.setCargo(vista.tfCargoE.getText());
            //modelo.setId_vehiculo((vista.cbVehiculoC.getSelectedIndex() + 1));
            modelo.setUsuario(vista.tfUsuarioE.getText());
            modelo.setContrasena(vista.tfContraE.getText());
            modelo.setRol((String) vista.cbRolE.getSelectedItem());
            try {
                modelo.setId(Integer.parseInt(vista.tfIdE.getText()));
                if (!vista.tfIdE.getText().equals("")) {
                    if (empleadoDTO.modificarEmpleado(modelo)) {
                        JOptionPane.showMessageDialog(null, "Registro Modificado");
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Modificar");
                        limpiar();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla");
                }

            } catch (Exception er) {
                System.err.println(er);
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla");
            }
        }
        if (e.getSource() == vista.btnListarE) {
            limpiarTabla();
            listar(vista.tbEmpleados);
            limpiar();
        }
        if (e.getSource() == vista.btnEliminarE) {
            try {
                modelo.setId(Integer.parseInt(vista.tfIdE.getText()));

                if (empleadoDTO.eliminarEmpleado(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro Eliminado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Eliminar");
                    limpiar();
                }
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro de la tabla");
            }
        }
    }
    public void mouseClicked(MouseEvent e) {
        // Manejar eventos de clic en la tabla
        if (e.getSource() == vista.tbEmpleados) {
            int filaSeleccionada = vista.tbEmpleados.getSelectedRow();
            // Obtener valor del campo ID de la fila seleccionada
            String id = vista.tbEmpleados.getValueAt(filaSeleccionada, 0).toString();
            // Realizar acciones con el valor del ID obtenido
            System.out.println(id);
            if (id != null) {
                var em = empleadoDTO.consultaEmpleado(Integer.parseInt(id));
                //JOptionPane.showMessageDialog(null, "consulta realizada");
                limpiar();
                vista.tfIdE.setText(Integer.toString(em.getId()));
                vista.tfNombresE.setText(em.getNombre());
                vista.tfApellidosE.setText(em.getApellido());
                vista.tfCargoE.setText(em.getCargo());
                vista.tfCedulaE.setText(em.getCedula());
                vista.tfTelefonoE.setText(em.getTelefono());
                vista.tfEmailE.setText(em.getEmail());
                //int index= devolverIndice(em.getRol());
                //vista.cbRolE.setSelectedIndex(devolverIndice(em.getRol()));
                vista.cbRolE.setSelectedItem(em.getRol());
                vista.tfUsuarioE.setText(em.getUsuario());
                vista.tfContraE.setText(em.getContrasena());
            } else {
                JOptionPane.showMessageDialog(null, "Error al consultar");
                //limpiar();
            }
        }
    }
    public void limpiar(){
        vista.tfNombresE.setText(null);
        vista.tfApellidosE.setText(null);
        vista.tfCargoE.setText(null);
        vista.tfCedulaE.setText(null);
        vista.tfTelefonoE.setText(null);
        vista.tfEmailE.setText(null);
        vista.cbRolE.setSelectedIndex(0);
        vista.tfUsuarioE.setText(null);
        vista.tfContraE.setText(null);
        vista.tfIdE.setText(null);
    }
    public void listar(JTable tbEmpleados) {
        centrarCeldas(tbEmpleados);
        model = (DefaultTableModel) tbEmpleados.getModel();
        tbEmpleados.setModel(model);
        List<Empleado> lista = empleadoDTO.listarEmpleados();
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

    void centrarCeldas(JTable tbEmpleados) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tbEmpleados.getColumnCount(); i++) {
            tbEmpleados.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tbEmpleados.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
    int devolverIndice(String opc){
        int index = 0;
        switch (opc) {
            case "admin":
                index = 1;
                break;
            case "standard":
                index = 2;
            default:
                throw new AssertionError();
        }
        return index;
    }
    String devolverString(int index){
        String txt = "";
        switch (index) {
            case 1:
                txt="admin";
                break;
            case 2:
                txt="standard";
            default:
                txt="seleccione una";
                throw new AssertionError();
        }
        return txt;
        
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
