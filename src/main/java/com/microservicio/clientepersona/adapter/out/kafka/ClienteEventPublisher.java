package com.microservicio.clientepersona.adapter.out.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClienteEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ClienteEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicarClienteCreado(String mensajeJson) {
        kafkaTemplate.send("cliente-creado-topic", mensajeJson);
    }
}
