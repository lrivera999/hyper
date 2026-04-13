package com.hyper.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "precios")
public class Precio {

    @Id
    private Integer idPrecio;

    private Integer idReserva;
    private Integer idHabitacion;
    private Integer esPromocion;
    private Integer esFeriado;
    private Integer tarifaAplicada;
    private String estado;

    public Integer getIdPrecio() { return idPrecio; }
    public void setIdPrecio(Integer idPrecio) { this.idPrecio = idPrecio; }
    public Integer getIdReserva() { return idReserva; }
    public void setIdReserva(Integer idReserva) { this.idReserva = idReserva; }
    public Integer getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(Integer idHabitacion) { this.idHabitacion = idHabitacion; }
    public Integer getEsPromocion() { return esPromocion; }
    public void setEsPromocion(Integer esPromocion) { this.esPromocion = esPromocion; }
    public Integer getEsFeriado() { return esFeriado; }
    public void setEsFeriado(Integer esFeriado) { this.esFeriado = esFeriado; }
    public Integer getTarifaAplicada() { return tarifaAplicada; }
    public void setTarifaAplicada(Integer tarifaAplicada) { this.tarifaAplicada = tarifaAplicada; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
