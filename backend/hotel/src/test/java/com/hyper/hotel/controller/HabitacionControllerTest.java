package com.hyper.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyper.hotel.model.Habitacion;
import com.hyper.hotel.security.JwtUtil;
import com.hyper.hotel.service.HabitacionService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HabitacionController.class)
class HabitacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HabitacionService service;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private Habitacion habitacion;

    @BeforeEach
    void setUp() {
        habitacion = new Habitacion();
        habitacion.setIdHabitacion(1);
        habitacion.setPiso(2);
        habitacion.setHabitacion(201);
        habitacion.setMenaje("Cama king, minibar");
        habitacion.setEstado("A");
    }

    @Test
    @WithMockUser
    void listar_retornaHabitaciones() throws Exception {
        when(service.listar()).thenReturn(List.of(habitacion));

        mockMvc.perform(get("/habitaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].piso").value(2))
                .andExpect(jsonPath("$[0].habitacion").value(201));
    }

    @Test
    @WithMockUser
    void disponibles_retornaHabitacionesDisponibles() throws Exception {
        when(service.disponibles()).thenReturn(List.of(habitacion));

        mockMvc.perform(get("/habitaciones/disponibles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("A"));
    }

    @Test
    @WithMockUser
    void buscarPorId_habitacionNoExistente_retorna404() throws Exception {
        when(service.buscarPorId(99))
                .thenThrow(new EntityNotFoundException("Habitación no encontrada: 99"));

        mockMvc.perform(get("/habitaciones/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void crear_habitacionValida_retorna201() throws Exception {
        when(service.guardar(any(Habitacion.class))).thenReturn(habitacion);

        mockMvc.perform(post("/habitaciones")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(habitacion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.habitacion").value(201));
    }

    @Test
    @WithMockUser
    void eliminar_habitacionExistente_retorna204() throws Exception {
        doNothing().when(service).eliminar(1);

        mockMvc.perform(delete("/habitaciones/1").with(csrf()))
                .andExpect(status().isNoContent());
    }
}
