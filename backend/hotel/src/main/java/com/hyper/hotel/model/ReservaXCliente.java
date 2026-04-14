package com.hyper.hotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reservaXclientes")
public class ReservaXCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_rxc")
    @TableGenerator(name = "gen_rxc", table = "id_gen",
            pkColumnName = "gen_name", valueColumnName = "gen_val",
            pkColumnValue = "reservaXclientes", allocationSize = 1)
    private Integer idReservaCliente;

    private Integer idReserva;
    private Integer idCliente;
    private String estado;

    public Integer getIdReservaCliente() { return idReservaCliente; }
    public void setIdReservaCliente(Integer idReservaCliente) { this.idReservaCliente = idReservaCliente; }
    public Integer getIdReserva() { return idReserva; }
    public void setIdReserva(Integer idReserva) { this.idReserva = idReserva; }
    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
