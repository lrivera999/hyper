package com.hyper.hotel.repository;

import com.hyper.hotel.model.ReservaXClienteXHab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaXClienteXHabRepository extends JpaRepository<ReservaXClienteXHab, Integer> {

    List<ReservaXClienteXHab> findByIdReservaCliente(Integer idReservaCliente);
    List<ReservaXClienteXHab> findByIdHabitacion(Integer idHabitacion);
}
