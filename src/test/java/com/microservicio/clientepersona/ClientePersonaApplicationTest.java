package com.microservicio.clientepersona;

import com.microservicio.clientepersona.application.service.ClienteServiceImpl;
import com.microservicio.clientepersona.domain.model.Cliente;
import com.microservicio.clientepersona.domain.port.out.ClienteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@SpringBootTest
public class ClientePersonaApplicationTest {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void guardarYConsultarCliente() {
        Cliente cliente = Cliente.builder()
                .clienteId("CL999")
                .nombre("Integración")
                .identificacion("1234567899")
                .edad(28)
                .direccion("Calle Integración 123")
                .telefono("0987654321")
                .contrasena("test123")
                .estado(true)
                .build();

        Cliente guardado = clienteService.guardar(cliente);

        assertNotNull(guardado);
        assertEquals("CL999", guardado.getClienteId());

        Optional<Cliente> encontrado = clienteRepository.findByClienteId("CL999");


        assertTrue(encontrado.isPresent());
        assertEquals("Integración", encontrado.get().getNombre());
    }
    @Test
    void actualizarClienteExistente() {
        Cliente cliente = clienteService.guardar(Cliente.builder()
                .clienteId("CL103")
                .nombre("Luis")
                .identificacion("0123456789")
                .edad(30)
                .direccion("Antigua")
                .telefono("099111111")
                .contrasena("pass")
                .estado(true)
                .build());

        cliente.setNombre("Luis Actualizado");
        cliente.setDireccion("Nueva dirección");

        Cliente actualizado = clienteService.guardar(cliente);

        assertEquals("Luis Actualizado", actualizado.getNombre());
    }

    @Test
    @Transactional
    void eliminarClientePorId() {
        clienteRepository.deleteByClienteId("CL997");
        Cliente cliente = clienteService.guardar(Cliente.builder()
                .clienteId("CL991")
                .nombre("Eliminar")
                .identificacion("1234567887")
                .edad(40)
                .direccion("Calle x")
                .telefono("099222222")
                .contrasena("pass")
                .estado(true)
                .build());

        clienteRepository.deleteByClienteId("CL991");

        Optional<Cliente> encontrado = clienteRepository.findByClienteId("CL991");

        assertFalse(encontrado.isPresent());
    }


}
