package com.hyper.hotel.controller;

import com.hyper.hotel.dto.EstadisticaCostoDTO;
import com.hyper.hotel.dto.EstadisticaReservaDTO;
import com.hyper.hotel.model.Reserva;
import com.hyper.hotel.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@Tag(name = "Reservas", description = "Gestión de reservas del hotel")
@SecurityRequirement(name = "bearerAuth")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todas las reservas")
    public List<Reserva> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reserva por ID")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear nueva reserva")
    public ResponseEntity<Reserva> crear(@Valid @RequestBody Reserva r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(r));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reserva existente")
    public ResponseEntity<Reserva> actualizar(@PathVariable Integer id,
                                               @Valid @RequestBody Reserva r) {
        return ResponseEntity.ok(service.actualizar(id, r));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reserva")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estadisticas/reservas-por-mes")
    @Operation(summary = "Cantidad de reservas agrupadas por mes y año")
    public List<EstadisticaReservaDTO> reservasPorMes() {
        return service.promedioReservasPorMesYAnio();
    }

    @GetMapping("/estadisticas/costo-promedio-por-mes")
    @Operation(summary = "Promedio de costo de reservas agrupado por mes y año")
    public List<EstadisticaCostoDTO> costoPorMes() {
        return service.promedioCostoPorMesYAnio();
    }
}
