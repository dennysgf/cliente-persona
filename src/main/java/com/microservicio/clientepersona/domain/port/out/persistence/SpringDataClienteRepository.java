package com.microservicio.clientepersona.domain.port.out.persistence;

import com.microservicio.clientepersona.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByClienteId(String clienteId);
    boolean existsByIdentificacion(String identificacion);
    Optional<Cliente> findByClienteId(String clienteId);
    void deleteByClienteId(String clienteId);

}
