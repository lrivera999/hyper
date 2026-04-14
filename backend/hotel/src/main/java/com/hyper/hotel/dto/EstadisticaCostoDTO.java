package com.hyper.hotel.dto;

public class EstadisticaCostoDTO {

    private String anio;
    private String mes;
    private double promedioCosto;

    public EstadisticaCostoDTO(String anio, String mes, double promedioCosto) {
        this.anio = anio;
        this.mes = mes;
        this.promedioCosto = promedioCosto;
    }

    public String getAnio() { return anio; }
    public String getMes() { return mes; }
    public double getPromedioCosto() { return promedioCosto; }
}
