package com.microservicio.clientepersona.domain.port.out;

import com.microservicio.clientepersona.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByClienteId(String clienteId);
    List<Cliente> findAll();
    void deleteById(Long id);
    void deleteByClienteId(String clienteId);
    boolean existsByClienteId(String clienteId);
    boolean existsByIdentificacion(String identificacion);
}
