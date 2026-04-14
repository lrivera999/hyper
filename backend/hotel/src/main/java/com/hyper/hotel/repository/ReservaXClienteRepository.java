package com.hyper.hotel.repository;

import com.hyper.hotel.model.ReservaXCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaXClienteRepository extends JpaRepository<ReservaXCliente, Integer> {

    List<ReservaXCliente> findByIdReserva(Integer idReserva);
    List<ReservaXCliente> findByIdCliente(Integer idCliente);
}
