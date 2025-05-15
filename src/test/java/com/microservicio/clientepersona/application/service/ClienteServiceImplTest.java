package com.microservicio.clientepersona.application.service;

import com.microservicio.clientepersona.adapter.out.kafka.ClienteEventPublisher;
import com.microservicio.clientepersona.domain.model.Cliente;
import com.microservicio.clientepersona.domain.port.out.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ClienteServiceImplTest {
    private ClienteRepository clienteRepository;
    private ClienteServiceImpl clienteService;
    private ClienteEventPublisher clienteEventPublisher;


    @BeforeEach
    void setUp() {
        clienteRepository = mock(ClienteRepository.class);
        clienteEventPublisher = mock(ClienteEventPublisher.class);
        clienteService = new ClienteServiceImpl(clienteRepository, clienteEventPublisher);
    }

    @Test
    void guardarClienteExitoso() {
        Cliente cliente = Cliente.builder()
                .clienteId("CL001")
                .nombre("Luis Lema")
                .identificacion("1234567890")
                .edad(30)
                .direccion("Av. Siempre Viva")
                .telefono("099999999")
                .contrasena("pass")
                .estado(true)
                .build();

        when(clienteRepository.existsByClienteId(cliente.getClienteId())).thenReturn(false);
        when(clienteRepository.existsByIdentificacion(cliente.getIdentificacion())).thenReturn(false);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.guardar(cliente);

        assertNotNull(resultado);
        assertEquals("CL001", resultado.getClienteId());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void guardarClienteDuplicadoPorId() {
        Cliente cliente = Cliente.builder()
                .clienteId("CL001")
                .nombre("Luis Lema")
                .identificacion("1234567890")
                .edad(30)
                .direccion("Av. Siempre Viva")
                .telefono("099999999")
                .contrasena("pass")
                .estado(true)
                .build();
        when(clienteRepository.existsByClienteId(cliente.getClienteId())).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> clienteService.guardar(cliente));
        assertEquals("El clienteId ya existe.", ex.getMessage());
    }

    @Test
    void guardarClienteDuplicadoPorIdentificacion() {
        Cliente cliente = Cliente.builder()
                .clienteId("CL001")
                .nombre("Luis Lema")
                .identificacion("1234567890")
                .edad(30)
                .direccion("Av. Siempre Viva")
                .telefono("099999999")
                .contrasena("pass")
                .estado(true)
                .build();
        when(clienteRepository.existsByClienteId(cliente.getClienteId())).thenReturn(false);
        when(clienteRepository.existsByIdentificacion(cliente.getIdentificacion())).thenReturn(true);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> clienteService.guardar(cliente));
        assertEquals("La identificación ya está registrada.", ex.getMessage());
    }
}
