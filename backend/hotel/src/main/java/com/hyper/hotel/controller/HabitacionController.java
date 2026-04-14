package com.hyper.hotel.controller;

import com.hyper.hotel.model.Habitacion;
import com.hyper.hotel.service.HabitacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitaciones")
@Tag(name = "Habitaciones", description = "Gestión de habitaciones del hotel")
@SecurityRequirement(name = "bearerAuth")
public class HabitacionController {

    private final HabitacionService service;

    public HabitacionController(HabitacionService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todas las habitaciones")
    public List<Habitacion> listar() {
        return service.listar();
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Consultar habitaciones disponibles (sin reserva activa hoy)")
    public List<Habitacion> disponibles() {
        return service.disponibles();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener habitación por ID")
    public ResponseEntity<Habitacion> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear nueva habitación")
    public ResponseEntity<Habitacion> crear(@Valid @RequestBody Habitacion h) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(h));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar habitación existente")
    public ResponseEntity<Habitacion> actualizar(@PathVariable Integer id,
                                                  @Valid @RequestBody Habitacion h) {
        return ResponseEntity.ok(service.actualizar(id, h));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar habitación")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
