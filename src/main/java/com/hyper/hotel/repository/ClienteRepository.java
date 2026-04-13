package com.hyper.hotel.repository;

import com.hyper.hotel.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {}
