package com.hyper.hotel.service;

import com.hyper.hotel.model.Habitacion;
import com.hyper.hotel.repository.HabitacionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitacionService {

    private final HabitacionRepository repo;

    public HabitacionService(HabitacionRepository repo) {
        this.repo = repo;
    }

    public List<Habitacion> listar() {
        return repo.findAll();
    }

    public Habitacion buscarPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habitación no encontrada: " + id));
    }

    public Habitacion guardar(Habitacion h) {
        return repo.save(h);
    }

    public Habitacion actualizar(Integer id, Habitacion datos) {
        Habitacion existente = buscarPorId(id);
        existente.setPiso(datos.getPiso());
        existente.setHabitacion(datos.getHabitacion());
        existente.setMenaje(datos.getMenaje());
        existente.setEstado(datos.getEstado());
        return repo.save(existente);
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Habitación no encontrada: " + id);
        }
        repo.deleteById(id);
    }

    public List<Habitacion> disponibles() {
        String hoy = LocalDate.now().toString();
        return repo.findDisponibles(hoy);
    }
}
