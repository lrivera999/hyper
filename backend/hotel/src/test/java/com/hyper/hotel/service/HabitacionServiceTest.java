package com.hyper.hotel.service;

import com.hyper.hotel.model.Habitacion;
import com.hyper.hotel.repository.HabitacionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HabitacionServiceTest {

    @Mock
    private HabitacionRepository repo;

    @InjectMocks
    private HabitacionService service;

    private Habitacion habitacion;

    @BeforeEach
    void setUp() {
        habitacion = new Habitacion();
        habitacion.setIdHabitacion(1);
        habitacion.setPiso(1);
        habitacion.setHabitacion(101);
        habitacion.setMenaje("Cama doble, TV, baño privado");
        habitacion.setEstado("A");
    }

    @Test
    void listar_retornaTodasLasHabitaciones() {
        when(repo.findAll()).thenReturn(List.of(habitacion));

        List<Habitacion> resultado = service.listar();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getPiso()).isEqualTo(1);
    }

    @Test
    void buscarPorId_habitacionExistente_retornaHabitacion() {
        when(repo.findById(1)).thenReturn(Optional.of(habitacion));

        Habitacion resultado = service.buscarPorId(1);

        assertThat(resultado.getIdHabitacion()).isEqualTo(1);
    }

    @Test
    void buscarPorId_habitacionNoExistente_lanzaEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void guardar_habitacionNueva_retornaHabitacionGuardada() {
        when(repo.save(habitacion)).thenReturn(habitacion);

        Habitacion resultado = service.guardar(habitacion);

        assertThat(resultado.getHabitacion()).isEqualTo(101);
        verify(repo).save(habitacion);
    }

    @Test
    void disponibles_retornaHabitacionesSinReservaActiva() {
        when(repo.findDisponibles(anyString())).thenReturn(List.of(habitacion));

        List<Habitacion> resultado = service.disponibles();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getEstado()).isEqualTo("A");
    }

    @Test
    void eliminar_habitacionNoExistente_lanzaEntityNotFoundException() {
        when(repo.existsById(99)).thenReturn(false);

        assertThatThrownBy(() -> service.eliminar(99))
                .isInstanceOf(EntityNotFoundException.class);
    }
}
