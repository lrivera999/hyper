package com.hyper.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyper.hotel.model.Cliente;
import com.hyper.hotel.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.hyper.hotel.security.JwtUtil;
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

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService service;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNombres("Ana");
        cliente.setApellidos("García");
        cliente.setEdad(28);
        cliente.setEstado("A");
    }

    @Test
    @WithMockUser
    void listar_retornaListaDeClientes() throws Exception {
        when(service.listar()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombres").value("Ana"))
                .andExpect(jsonPath("$[0].apellidos").value("García"));
    }

    @Test
    @WithMockUser
    void buscarPorId_clienteExistente_retorna200() throws Exception {
        when(service.buscarPorId(1)).thenReturn(cliente);

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Ana"));
    }

    @Test
    @WithMockUser
    void buscarPorId_clienteNoExistente_retorna404() throws Exception {
        when(service.buscarPorId(99)).thenThrow(new EntityNotFoundException("Cliente no encontrado: 99"));

        mockMvc.perform(get("/clientes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void crear_clienteValido_retorna201() throws Exception {
        when(service.guardar(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombres").value("Ana"));
    }

    @Test
    @WithMockUser
    void eliminar_clienteExistente_retorna204() throws Exception {
        doNothing().when(service).eliminar(1);

        mockMvc.perform(delete("/clientes/1").with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void eliminar_clienteNoExistente_retorna404() throws Exception {
        doThrow(new EntityNotFoundException("Cliente no encontrado: 99"))
                .when(service).eliminar(99);

        mockMvc.perform(delete("/clientes/99").with(csrf()))
                .andExpect(status().isNotFound());
    }
}
