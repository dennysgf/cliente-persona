
CREATE DATABASE IF NOT EXISTS clientepersonadb;
\c clientepersonadb;


CREATE TABLE IF NOT EXISTS cliente (
                                       id VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    telefono VARCHAR(20),
    correo VARCHAR(100)
    );


INSERT INTO cliente (id, nombre, direccion, telefono, correo) VALUES
                                                                  ('CL108', 'Juan Pérez', 'Av. América y 10 de Agosto', '0999999999', 'juan.perez@example.com'),
                                                                  ('CL123', 'María Gómez', 'Calle Larga y Hermano Miguel', '0988888888', 'maria.gomez@example.com'),
                                                                  ('CL200', 'Carlos Ríos', 'Av. Amazonas y Eloy Alfaro', '0977777777', 'carlos.rios@example.com');
