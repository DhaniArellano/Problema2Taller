/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author DHANI
 */
public class Vehiculo {
    private int id;
    private String placa;
    private String tipo;
    private String estado;
    private String motivoIngreso;
    private Date fechaIngreso;
    private Date fechaEntrega;
    private ArrayList<Servicio> servicios;
    private ArrayList<Turno> turnos;

    public Vehiculo() {
    }
    

    public Vehiculo(int id, String placa, String tipo, String estado, String motivoIngreso, Date fechaIngreso, Date fechaEntrega) {
        this.id = id;
        this.placa = placa;
        this.tipo = tipo;
        this.estado = estado;
        this.motivoIngreso = motivoIngreso;
        this.fechaIngreso = fechaIngreso;
        this.fechaEntrega = fechaEntrega;
        servicios = new ArrayList<>();
        turnos = new ArrayList<>();
    }
    public List<Turno> getTurnos() {
        return turnos;
    }
    
    public void setTurnos(List<Turno> turnos) {
        this.turnos = (ArrayList<Turno>) turnos;
    }
    
    public void addTurno(Turno turno) {
        this.turnos.add(turno);
    }
    
    public void removeTurno(Turno turno) {
        this.turnos.remove(turno);
    }
    public void agregarServicio(Servicio servicio) {
        servicios.add(servicio);
    }
    
    public void eliminarServicio(Servicio servicio) {
        servicios.remove(servicio);
    }
    
    public ArrayList<Servicio> getServicios() {
        return servicios;
    }
    
    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the motivoIngreso
     */
    public String getMotivoIngreso() {
        return motivoIngreso;
    }

    /**
     * @param motivoIngreso the motivoIngreso to set
     */
    public void setMotivoIngreso(String motivoIngreso) {
        this.motivoIngreso = motivoIngreso;
    }

    /**
     * @return the fechaIngreso
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaEntrega
     */
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
