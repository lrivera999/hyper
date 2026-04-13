package com.hyper.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    private Integer idHabitacion;

    private Integer piso;
    private Integer habitacion;
    private String menaje;
    private String estado;

    public Integer getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(Integer idHabitacion) { this.idHabitacion = idHabitacion; }
    public Integer getPiso() { return piso; }
    public void setPiso(Integer piso) { this.piso = piso; }
    public Integer getHabitacion() { return habitacion; }
    public void setHabitacion(Integer habitacion) { this.habitacion = habitacion; }
    public String getMenaje() { return menaje; }
    public void setMenaje(String menaje) { this.menaje = menaje; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
