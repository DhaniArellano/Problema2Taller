/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DHANI
 */
public class Servicio {
    private int id;
    private String tipoServicio;
    private String descripcion;
    private double tiempoEstimado; // En minutos
    private double precio;

    public Servicio() {
    }

    public Servicio(int id, String tipoServicio, String descripcion, double tiempoEstimado, double precio) {
        this.id = id;
        this.tipoServicio = tipoServicio;
        this.descripcion = descripcion;
        this.tiempoEstimado = tiempoEstimado;
        this.precio = precio;
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

    /**
     * @return the tipoServicio
     */
    public String getTipoServicio() {
        return tipoServicio;
    }

    /**
     * @param tipoServicio the tipoServicio to set
     */
    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the tiempoEstimado
     */
    public double getTiempoEstimado() {
        return tiempoEstimado;
    }

    /**
     * @param tiempoEstimado the tiempoEstimado to set
     */
    public void setTiempoEstimado(double tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    


}
