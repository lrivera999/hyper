package com.hyper.hotel.service;

import com.hyper.hotel.model.Horario;
import com.hyper.hotel.repository.HorarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    private final HorarioRepository repo;

    public HorarioService(HorarioRepository repo) {
        this.repo = repo;
    }

    public List<Horario> listar() {
        return repo.findAll();
    }

    public Horario buscarPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Horario no encontrado: " + id));
    }

    public Horario guardar(Horario h) {
        return repo.save(h);
    }

    public Horario actualizar(Integer id, Horario datos) {
        Horario existente = buscarPorId(id);
        existente.setIdHabitacion(datos.getIdHabitacion());
        existente.setHoraIngreso(datos.getHoraIngreso());
        existente.setHoraSalida(datos.getHoraSalida());
        existente.setEstado(datos.getEstado());
        return repo.save(existente);
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Horario no encontrado: " + id);
        }
        repo.deleteById(id);
    }
}
