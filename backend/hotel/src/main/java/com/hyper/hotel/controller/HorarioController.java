package com.hyper.hotel.controller;

import com.hyper.hotel.model.Horario;
import com.hyper.hotel.service.HorarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
@Tag(name = "Horarios", description = "Gestión de horarios de habitaciones")
@SecurityRequirement(name = "bearerAuth")
public class HorarioController {

    private final HorarioService service;

    public HorarioController(HorarioService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos los horarios")
    public List<Horario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener horario por ID")
    public ResponseEntity<Horario> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo horario")
    public ResponseEntity<Horario> crear(@Valid @RequestBody Horario h) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(h));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar horario existente")
    public ResponseEntity<Horario> actualizar(@PathVariable Integer id,
                                               @Valid @RequestBody Horario h) {
        return ResponseEntity.ok(service.actualizar(id, h));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar horario")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
