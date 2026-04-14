package com.hyper.hotel.dto;

public class EstadisticaReservaDTO {

    private String anio;
    private String mes;
    private long cantidad;

    public EstadisticaReservaDTO(String anio, String mes, long cantidad) {
        this.anio = anio;
        this.mes = mes;
        this.cantidad = cantidad;
    }

    public String getAnio() { return anio; }
    public String getMes() { return mes; }
    public long getCantidad() { return cantidad; }
}
