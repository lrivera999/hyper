package com.hyper.hotel.service;

import com.hyper.hotel.dto.EstadisticaCostoDTO;
import com.hyper.hotel.dto.EstadisticaReservaDTO;
import com.hyper.hotel.model.Reserva;
import com.hyper.hotel.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repo;

    public ReservaService(ReservaRepository repo) {
        this.repo = repo;
    }

    public List<Reserva> listar() {
        return repo.findAll();
    }

    public Reserva buscarPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada: " + id));
    }

    public Reserva guardar(Reserva r) {
        return repo.save(r);
    }

    public Reserva actualizar(Integer id, Reserva datos) {
        Reserva existente = buscarPorId(id);
        existente.setNumHuesped(datos.getNumHuesped());
        existente.setFechaInicio(datos.getFechaInicio());
        existente.setFechaFin(datos.getFechaFin());
        existente.setEstado(datos.getEstado());
        return repo.save(existente);
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Reserva no encontrada: " + id);
        }
        repo.deleteById(id);
    }

    public List<EstadisticaReservaDTO> promedioReservasPorMesYAnio() {
        return repo.contarReservasPorMesYAnio().stream()
                .map(row -> new EstadisticaReservaDTO(
                        String.valueOf(row[0]),
                        String.valueOf(row[1]),
                        ((Number) row[2]).longValue()))
                .toList();
    }

    public List<EstadisticaCostoDTO> promedioCostoPorMesYAnio() {
        return repo.promedioCostoPorMesYAnio().stream()
                .map(row -> new EstadisticaCostoDTO(
                        String.valueOf(row[0]),
                        String.valueOf(row[1]),
                        row[2] != null ? ((Number) row[2]).doubleValue() : 0.0))
                .toList();
    }
}
