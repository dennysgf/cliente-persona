package com.microservicio.clientepersona.domain.port.out.persistence;

import com.microservicio.clientepersona.domain.model.Cliente;
import com.microservicio.clientepersona.domain.port.out.ClienteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {
    private final SpringDataClienteRepository jpaRepository;

    public ClienteRepositoryImpl(SpringDataClienteRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return jpaRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void deleteByClienteId(String clienteId) {
        jpaRepository.deleteByClienteId(clienteId);
    }
    @Override
    public Optional<Cliente> findByClienteId(String clienteId) {
        return jpaRepository.findByClienteId(clienteId);
    }

    @Override
    public boolean existsByClienteId(String clienteId) {
        return jpaRepository.existsByClienteId(clienteId);
    }

    @Override
    public boolean existsByIdentificacion(String identificacion) {
        return jpaRepository.existsByIdentificacion(identificacion);
    }
}
