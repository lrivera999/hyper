package com.hyper.hotel.service;

import com.hyper.hotel.model.Cliente;
import com.hyper.hotel.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + id));
    }

    public Cliente guardar(Cliente c) {
        return repo.save(c);
    }

    public Cliente actualizar(Integer id, Cliente datos) {
        Cliente existente = buscarPorId(id);
        existente.setNombres(datos.getNombres());
        existente.setApellidos(datos.getApellidos());
        existente.setEdad(datos.getEdad());
        existente.setEstado(datos.getEstado());
        return repo.save(existente);
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Cliente no encontrado: " + id);
        }
        repo.deleteById(id);
    }
}
