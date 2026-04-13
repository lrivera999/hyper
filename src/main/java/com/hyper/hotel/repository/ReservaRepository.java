package com.hyper.hotel.repository;

import com.hyper.hotel.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {}
