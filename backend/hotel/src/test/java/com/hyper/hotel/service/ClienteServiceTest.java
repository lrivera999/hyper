package com.hyper.hotel.service;

import com.hyper.hotel.model.Cliente;
import com.hyper.hotel.repository.ClienteRepository;
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
class ClienteServiceTest {

    @Mock
    private ClienteRepository repo;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNombres("Juan");
        cliente.setApellidos("Pérez");
        cliente.setEdad(30);
        cliente.setEstado("A");
    }

    @Test
    void listar_retornaListaDeClientes() {
        when(repo.findAll()).thenReturn(List.of(cliente));

        List<Cliente> resultado = service.listar();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombres()).isEqualTo("Juan");
    }

    @Test
    void buscarPorId_clienteExistente_retornaCliente() {
        when(repo.findById(1)).thenReturn(Optional.of(cliente));

        Cliente resultado = service.buscarPorId(1);

        assertThat(resultado.getIdCliente()).isEqualTo(1);
        assertThat(resultado.getNombres()).isEqualTo("Juan");
    }

    @Test
    void buscarPorId_clienteNoExistente_lanzaEntityNotFoundException() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void guardar_clienteNuevo_retornaClienteGuardado() {
        when(repo.save(cliente)).thenReturn(cliente);

        Cliente resultado = service.guardar(cliente);

        assertThat(resultado.getNombres()).isEqualTo("Juan");
        verify(repo, times(1)).save(cliente);
    }

    @Test
    void actualizar_clienteExistente_actualizaDatos() {
        Cliente datosNuevos = new Cliente();
        datosNuevos.setNombres("Pedro");
        datosNuevos.setApellidos("López");
        datosNuevos.setEdad(25);
        datosNuevos.setEstado("A");

        when(repo.findById(1)).thenReturn(Optional.of(cliente));
        when(repo.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = service.actualizar(1, datosNuevos);

        verify(repo).save(any(Cliente.class));
        assertThat(resultado).isNotNull();
    }

    @Test
    void eliminar_clienteExistente_eliminaCorrectamente() {
        when(repo.existsById(1)).thenReturn(true);
        doNothing().when(repo).deleteById(1);

        assertThatCode(() -> service.eliminar(1)).doesNotThrowAnyException();
        verify(repo).deleteById(1);
    }

    @Test
    void eliminar_clienteNoExistente_lanzaEntityNotFoundException() {
        when(repo.existsById(99)).thenReturn(false);

        assertThatThrownBy(() -> service.eliminar(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }
}
