package com.microservicio.clientepersona.domain.port.in;

import com.microservicio.clientepersona.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente guardar(Cliente cliente);
    List<Cliente> obtenerTodos();
    Optional<Cliente> obtenerPorId(Long id);
    Cliente actualizar(Long id, Cliente cliente);
    void eliminar(Long id);
}
