package com.hyper.hotel.service;

import com.hyper.hotel.model.ReservaXCliente;
import com.hyper.hotel.repository.ReservaXClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaXClienteService {

    private final ReservaXClienteRepository repo;

    public ReservaXClienteService(ReservaXClienteRepository repo) {
        this.repo = repo;
    }

    public List<ReservaXCliente> listar() {
        return repo.findAll();
    }

    public ReservaXCliente buscarPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ReservaXCliente no encontrado: " + id));
    }

    public ReservaXCliente guardar(ReservaXCliente rxc) {
        return repo.save(rxc);
    }

    public ReservaXCliente actualizar(Integer id, ReservaXCliente datos) {
        ReservaXCliente existente = buscarPorId(id);
        existente.setIdReserva(datos.getIdReserva());
        existente.setIdCliente(datos.getIdCliente());
        existente.setEstado(datos.getEstado());
        return repo.save(existente);
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("ReservaXCliente no encontrado: " + id);
        }
        repo.deleteById(id);
    }
}
