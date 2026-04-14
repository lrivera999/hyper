package com.hyper.hotel.repository;

import com.hyper.hotel.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

    List<Habitacion> findByEstado(String estado);

    /**
     * Devuelve habitaciones activas que NO tienen reserva activa en la fecha dada.
     * Una habitacion está reservada si aparece en precios vinculada a una reserva
     * cuyo estado='A' y fechaFin >= :hoy.
     */
    @Query(value = """
            SELECT h.* FROM habitaciones h
            WHERE h.estado = 'A'
              AND h.idHabitacion NOT IN (
                  SELECT p.idHabitacion FROM precios p
                  INNER JOIN reservas r ON p.idReserva = r.idReserva
                  WHERE r.estado = 'A'
                    AND r.fechaFin >= :hoy
              )
            """, nativeQuery = true)
    List<Habitacion> findDisponibles(@Param("hoy") String hoy);
}
