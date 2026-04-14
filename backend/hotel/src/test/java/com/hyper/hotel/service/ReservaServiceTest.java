package com.hyper.hotel.service;

import com.hyper.hotel.dto.EstadisticaCostoDTO;
import com.hyper.hotel.dto.EstadisticaReservaDTO;
import com.hyper.hotel.model.Reserva;
import com.hyper.hotel.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository repo;

    @InjectMocks
    private ReservaService service;

    private Reserva reserva;

    @BeforeEach
    void setUp() {
        reserva = new Reserva();
        reserva.setIdReserva(1);
        reserva.setNumHuesped(2);
        reserva.setFechaInicio("2025-01-10");
        reserva.setFechaFin("2025-01-15");
        reserva.setEstado("A");
    }

    @Test
    void listar_retornaTodasLasReservas() {
        when(repo.findAll()).thenReturn(List.of(reserva));

        List<Reserva> resultado = service.listar();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNumHuesped()).isEqualTo(2);
    }

    @Test
    void buscarPorId_reservaExistente_retornaReserva() {
        when(repo.findById(1)).thenReturn(Optional.of(reserva));

        Reserva resultado = service.buscarPorId(1);

        assertThat(resultado.getIdReserva()).isEqualTo(1);
        assertThat(resultado.getFechaInicio()).isEqualTo("2025-01-10");
    }

    @Test
    void buscarPorId_reservaNoExistente_lanzaEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void guardar_reservaNueva_retornaReservaGuardada() {
        when(repo.save(reserva)).thenReturn(reserva);

        Reserva resultado = service.guardar(reserva);

        assertThat(resultado.getEstado()).isEqualTo("A");
        verify(repo).save(reserva);
    }

    @Test
    void eliminar_reservaNoExistente_lanzaEntityNotFoundException() {
        when(repo.existsById(99)).thenReturn(false);

        assertThatThrownBy(() -> service.eliminar(99))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void promedioReservasPorMesYAnio_retornaListaEstadisticas() {
        List<Object[]> filas = Collections.singletonList(new Object[]{"2025", "01", 5L});
        when(repo.contarReservasPorMesYAnio()).thenReturn(filas);

        List<EstadisticaReservaDTO> resultado = service.promedioReservasPorMesYAnio();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getAnio()).isEqualTo("2025");
        assertThat(resultado.get(0).getMes()).isEqualTo("01");
        assertThat(resultado.get(0).getCantidad()).isEqualTo(5L);
    }

    @Test
    void promedioCostoPorMesYAnio_retornaListaEstadisticas() {
        List<Object[]> filas = Collections.singletonList(new Object[]{"2025", "01", 150.0});
        when(repo.promedioCostoPorMesYAnio()).thenReturn(filas);

        List<EstadisticaCostoDTO> resultado = service.promedioCostoPorMesYAnio();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getAnio()).isEqualTo("2025");
        assertThat(resultado.get(0).getPromedioCosto()).isEqualTo(150.0);
    }
}
