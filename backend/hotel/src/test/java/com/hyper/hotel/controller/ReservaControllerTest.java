package com.hyper.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyper.hotel.dto.EstadisticaCostoDTO;
import com.hyper.hotel.dto.EstadisticaReservaDTO;
import com.hyper.hotel.model.Reserva;
import com.hyper.hotel.security.JwtUtil;
import com.hyper.hotel.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReservaService service;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private Reserva reserva;

    @BeforeEach
    void setUp() {
        reserva = new Reserva();
        reserva.setIdReserva(1);
        reserva.setNumHuesped(3);
        reserva.setFechaInicio("2025-03-01");
        reserva.setFechaFin("2025-03-05");
        reserva.setEstado("A");
    }

    @Test
    @WithMockUser
    void listar_retornaReservas() throws Exception {
        when(service.listar()).thenReturn(List.of(reserva));

        mockMvc.perform(get("/reservas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numHuesped").value(3));
    }

    @Test
    @WithMockUser
    void crear_reservaValida_retorna201() throws Exception {
        when(service.guardar(any(Reserva.class))).thenReturn(reserva);

        mockMvc.perform(post("/reservas")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reserva)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estado").value("A"));
    }

    @Test
    @WithMockUser
    void estadisticasReservasPorMes_retornaLista() throws Exception {
        List<EstadisticaReservaDTO> stats = List.of(
                new EstadisticaReservaDTO("2025", "03", 4L)
        );
        when(service.promedioReservasPorMesYAnio()).thenReturn(stats);

        mockMvc.perform(get("/reservas/estadisticas/reservas-por-mes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].anio").value("2025"))
                .andExpect(jsonPath("$[0].mes").value("03"))
                .andExpect(jsonPath("$[0].cantidad").value(4));
    }

    @Test
    @WithMockUser
    void estadisticasCostoPorMes_retornaLista() throws Exception {
        List<EstadisticaCostoDTO> stats = List.of(
                new EstadisticaCostoDTO("2025", "03", 200.0)
        );
        when(service.promedioCostoPorMesYAnio()).thenReturn(stats);

        mockMvc.perform(get("/reservas/estadisticas/costo-promedio-por-mes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].promedioCosto").value(200.0));
    }

    @Test
    @WithMockUser
    void eliminar_reservaExistente_retorna204() throws Exception {
        doNothing().when(service).eliminar(1);

        mockMvc.perform(delete("/reservas/1").with(csrf()))
                .andExpect(status().isNoContent());
    }
}
