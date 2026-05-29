CREATE TABLE ordenes (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     numero_orden VARCHAR(50) NOT NULL UNIQUE,
     fecha_ingreso DATE NOT NULL,
     estado VARCHAR(50) NOT NULL,
     cliente_id BIGINT NOT NULL,
     instrumento_id BIGINT NOT NULL,
     usuario_id BIGINT NOT NULL
);