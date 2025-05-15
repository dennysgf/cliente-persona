package com.microservicio.clientepersona.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.clientepersona.adapter.out.kafka.ClienteEventPublisher;
import com.microservicio.clientepersona.domain.model.Cliente;
import com.microservicio.clientepersona.domain.port.in.ClienteService;
import com.microservicio.clientepersona.domain.port.out.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteEventPublisher clienteEventPublisher;

    public ClienteServiceImpl(
            ClienteRepository clienteRepository,
            ClienteEventPublisher clienteEventPublisher ) {
        this.clienteRepository = clienteRepository;
        this.clienteEventPublisher = clienteEventPublisher;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findByClienteId(cliente.getClienteId());

        if (clienteExistente.isPresent()) {

            Cliente actual = clienteExistente.get();

            if (!cliente.getIdentificacion().equals(actual.getIdentificacion()) &&
                    clienteRepository.existsByIdentificacion(cliente.getIdentificacion())) {
                throw new IllegalArgumentException("La identificación ya está registrada por otro cliente.");
            }

            return clienteRepository.save(cliente);
        } else {

            if (clienteRepository.existsByIdentificacion(cliente.getIdentificacion())) {
                throw new IllegalArgumentException("La identificación ya está registrada.");
            }
            Cliente clienteGuardado = clienteRepository.save(cliente);

            try {
                String mensaje = new ObjectMapper().writeValueAsString(clienteGuardado);
                clienteEventPublisher.publicarClienteCreado(mensaje);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error al convertir cliente a JSON para mensajería Kafka", e);
            }

            return clienteGuardado;
        }
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {
        if (cliente.getId() != null && !cliente.getId().equals(id)) {
            throw new IllegalArgumentException("El ID del cuerpo no coincide con el de la URL.");
        }

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + id));

        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setEdad(cliente.getEdad());
        clienteExistente.setIdentificacion(cliente.getIdentificacion());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setClienteId(cliente.getClienteId());
        clienteExistente.setContrasena(cliente.getContrasena());
        clienteExistente.setEstado(cliente.getEstado());

        return clienteRepository.save(clienteExistente);
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
