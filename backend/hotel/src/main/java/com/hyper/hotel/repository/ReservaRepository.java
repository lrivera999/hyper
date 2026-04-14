package com.hyper.hotel.repository;

import com.hyper.hotel.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    /**
     * Cantidad de reservas agrupadas por año y mes (basado en fechaInicio).
     * Retorna Object[]: [anio, mes, cantidad]
     */
    @Query(value = """
            SELECT substr(r.fechaInicio, 1, 4)  AS anio,
                   substr(r.fechaInicio, 6, 2)  AS mes,
                   COUNT(*)                      AS cantidad
            FROM reservas r
            GROUP BY substr(r.fechaInicio, 1, 4), substr(r.fechaInicio, 6, 2)
            ORDER BY anio, mes
            """, nativeQuery = true)
    List<Object[]> contarReservasPorMesYAnio();

    /**
     * Promedio de costo (tarifaAplicada) de reservas agrupado por año y mes.
     * Retorna Object[]: [anio, mes, promedioCosto]
     */
    @Query(value = """
            SELECT substr(r.fechaInicio, 1, 4)   AS anio,
                   substr(r.fechaInicio, 6, 2)   AS mes,
                   AVG(p.tarifaAplicada)          AS promedioCosto
            FROM reservas r
            INNER JOIN precios p ON p.idReserva = r.idReserva
            GROUP BY substr(r.fechaInicio, 1, 4), substr(r.fechaInicio, 6, 2)
            ORDER BY anio, mes
            """, nativeQuery = true)
    List<Object[]> promedioCostoPorMesYAnio();
}
