package com.hyper.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    private Integer idHorario;

    private Integer idHabitacion;
    private String horaIngreso;
    private String horaSalida;
    private String estado;

    public Integer getIdHorario() { return idHorario; }
    public void setIdHorario(Integer idHorario) { this.idHorario = idHorario; }
    public Integer getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(Integer idHabitacion) { this.idHabitacion = idHabitacion; }
    public String getHoraIngreso() { return horaIngreso; }
    public void setHoraIngreso(String horaIngreso) { this.horaIngreso = horaIngreso; }
    public String getHoraSalida() { return horaSalida; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
