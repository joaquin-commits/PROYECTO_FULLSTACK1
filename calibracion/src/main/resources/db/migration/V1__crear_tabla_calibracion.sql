CREATE TABLE calibracion (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     codigo_calibracion VARCHAR(50) NOT NULL UNIQUE,
     fecha_calibracion DATE NOT NULL,
     resultado_medicion VARCHAR(255) NOT NULL,
     orden_id BIGINT NOT NULL,
     instrumento_id BIGINT NOT NULL,
     patron_id BIGINT NOT NULL,
     usuario_id BIGINT NOT NULL
);