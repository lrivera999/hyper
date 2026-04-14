package com.hyper.hotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reservaXclienteXHab")
public class ReservaXClienteXHab {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_rxch")
    @TableGenerator(name = "gen_rxch", table = "id_gen",
            pkColumnName = "gen_name", valueColumnName = "gen_val",
            pkColumnValue = "reservaXclienteXHab", allocationSize = 1)
    private Integer idReservaCliHab;

    private Integer idReservaCliente;
    private Integer idHabitacion;
    private String estado;

    public Integer getIdReservaCliHab() { return idReservaCliHab; }
    public void setIdReservaCliHab(Integer idReservaCliHab) { this.idReservaCliHab = idReservaCliHab; }
    public Integer getIdReservaCliente() { return idReservaCliente; }
    public void setIdReservaCliente(Integer idReservaCliente) { this.idReservaCliente = idReservaCliente; }
    public Integer getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(Integer idHabitacion) { this.idHabitacion = idHabitacion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
