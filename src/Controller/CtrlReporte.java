/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author DHANI
 */
import Model.Reporte;
import Model.ReporteDTO;
import View.GestionReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;

public class CtrlReporte extends MouseAdapter implements ActionListener {
    private final Reporte modelo;
    private final ReporteDTO reporteDTO;
    private final GestionReportes vista;
    private DefaultTableModel model = new DefaultTableModel();
    
    
    public CtrlReporte(Reporte modelo, ReporteDTO reporteDTO, GestionReportes vista){
        this.modelo = modelo;
        this.reporteDTO = reporteDTO;
        this.vista = vista;
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnImprimir.addActionListener(this);
        this.vista.btnReset.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
    }
    public void iniciar() {
        vista.setTitle("Gestion Reportes");
        vista.setLocationRelativeTo(null);
        cargarInformacion();
    }
    public void cerrar() {
        System.out.println("cerrando");
        vista.dispose();
    }
    public void cargarInformacion(){
        limpiarTabla();
        listar(vista.tbReporte);
        try{
            vista.tfTiempoEmpleado.setText(Float.toString(reporteDTO.totalSumaTiempo()));
            vista.tfTotalGanancias.setText(Float.toString(reporteDTO.totalSumaPrecio()));
            vista.tfTotalServicios.setText(Integer.toString(reporteDTO.numeroServicios()));
            vista.tfTotalVehiculos.setText(Integer.toString(reporteDTO.vehiculosAtendidos()));
        }catch(Exception ex){
            System.err.println(ex);
        }
    }
    public void listar(JTable tbReporte) {
        centrarCeldas(tbReporte);
        model = (DefaultTableModel) tbReporte.getModel();
        tbReporte.setModel(model);
        List<Object[]> lista = reporteDTO.listarInformacion();
        for (Object[] fila : lista) {
            model.addRow(fila);
        }
        //tbReporte.setRowHeight(35);
        //tbReporte.setRowMargin(10);
    }
    void centrarCeldas(JTable tbReporte) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tbReporte.getColumnCount(); i++) {
            tbReporte.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tbReporte.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (e.getSource() == vista.btnImprimir) {
            System.out.println("imprimir");
        }
        if (e.getSource() == vista.btnReset) {
            System.out.println("Restablecer");
            cargarInformacion();
            JOptionPane.showConfirmDialog(null, "está seguro de restablecer y borrar todo?", "Restablecer DB", 0);
        }
        if (e.getSource() == vista.btnActualizar) {
            System.out.println("actualizar");
            limpiarTabla();
            cargarInformacion();
        }
        if (e.getSource() == vista.btnSalir) {
            System.out.println("salir");
            cerrar();
        }
    }
    
    
}
