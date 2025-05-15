package com.microservicio.clientepersona.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper=true)
public class Cliente extends Persona {

    @Column(unique=true, nullable=false)
    private String clienteId;
    private String contrasena;
    private Boolean estado;

}
