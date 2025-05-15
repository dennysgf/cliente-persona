package com.microservicio.clientepersona.adapter.in.rest;

import com.microservicio.clientepersona.domain.model.Cliente;
import com.microservicio.clientepersona.domain.port.in.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
@Validated
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @PostMapping
    public ResponseEntity<Cliente>guardar(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(clienteService.guardar(cliente));
    }
    @GetMapping
    public ResponseEntity<List<Cliente>>listar() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Long id) {
        return clienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizar(id, cliente));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        try {
            clienteService.eliminar(id);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Cliente eliminado exitosamente con ID: " + id);
            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }


}
