package com.hyper.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    private Integer idReserva;

    private Integer numHuesped;
    private String fechaInicio;
    private String fechaFin;
    private String estado;

    public Integer getIdReserva() { return idReserva; }
    public void setIdReserva(Integer idReserva) { this.idReserva = idReserva; }
    public Integer getNumHuesped() { return numHuesped; }
    public void setNumHuesped(Integer numHuesped) { this.numHuesped = numHuesped; }
    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }
    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
