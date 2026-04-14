package com.hyper.hotel.controller;

import com.hyper.hotel.model.Precio;
import com.hyper.hotel.service.PrecioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/precios")
@Tag(name = "Precios", description = "Gestión de precios y tarifas de reservas")
@SecurityRequirement(name = "bearerAuth")
public class PrecioController {

    private final PrecioService service;

    public PrecioController(PrecioService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos los precios")
    public List<Precio> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener precio por ID")
    public ResponseEntity<Precio> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo precio")
    public ResponseEntity<Precio> crear(@Valid @RequestBody Precio p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(p));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar precio existente")
    public ResponseEntity<Precio> actualizar(@PathVariable Integer id,
                                              @Valid @RequestBody Precio p) {
        return ResponseEntity.ok(service.actualizar(id, p));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar precio")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
