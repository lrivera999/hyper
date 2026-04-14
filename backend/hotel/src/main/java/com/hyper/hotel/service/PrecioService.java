package com.hyper.hotel.service;

import com.hyper.hotel.model.Precio;
import com.hyper.hotel.repository.PrecioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecioService {

    private final PrecioRepository repo;

    public PrecioService(PrecioRepository repo) {
        this.repo = repo;
    }

    public List<Precio> listar() {
        return repo.findAll();
    }

    public Precio buscarPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Precio no encontrado: " + id));
    }

    public Precio guardar(Precio p) {
        return repo.save(p);
    }

    public Precio actualizar(Integer id, Precio datos) {
        Precio existente = buscarPorId(id);
        existente.setIdReserva(datos.getIdReserva());
        existente.setIdHabitacion(datos.getIdHabitacion());
        existente.setEsPromocion(datos.getEsPromocion());
        existente.setEsFeriado(datos.getEsFeriado());
        existente.setTarifaAplicada(datos.getTarifaAplicada());
        existente.setEstado(datos.getEstado());
        return repo.save(existente);
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Precio no encontrado: " + id);
        }
        repo.deleteById(id);
    }
}
