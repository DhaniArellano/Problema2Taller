/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DHANI
 */
public class Reporte {
    private float totalServicios;
    private float totalGanancias;
    private int totalVehiculos;
    private float totalTiempo;

    public Reporte() {
    }

    public Reporte(float totalServicios, float totalGanancias, int totalVehiculos, float totalTiempo) {
        this.totalServicios = totalServicios;
        this.totalGanancias = totalGanancias;
        this.totalVehiculos = totalVehiculos;
        this.totalTiempo = totalTiempo;
    }

    /**
     * @return the totalServicios
     */
    public float getTotalServicios() {
        return totalServicios;
    }

    /**
     * @param totalServicios the totalServicios to set
     */
    public void setTotalServicios(float totalServicios) {
        this.totalServicios = totalServicios;
    }

    /**
     * @return the totalGanancias
     */
    public float getTotalGanancias() {
        return totalGanancias;
    }

    /**
     * @param totalGanancias the totalGanancias to set
     */
    public void setTotalGanancias(float totalGanancias) {
        this.totalGanancias = totalGanancias;
    }

    /**
     * @return the totalVehiculos
     */
    public int getTotalVehiculos() {
        return totalVehiculos;
    }

    /**
     * @param totalVehiculos the totalVehiculos to set
     */
    public void setTotalVehiculos(int totalVehiculos) {
        this.totalVehiculos = totalVehiculos;
    }

    /**
     * @return the totalTiempo
     */
    public float getTotalTiempo() {
        return totalTiempo;
    }

    /**
     * @param totalTiempo the totalTiempo to set
     */
    public void setTotalTiempo(float totalTiempo) {
        this.totalTiempo = totalTiempo;
    }
    
}
