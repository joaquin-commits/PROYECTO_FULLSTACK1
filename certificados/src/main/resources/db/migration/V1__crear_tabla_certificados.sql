CREATE TABLE certificados (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      numero_certificado VARCHAR(50) NOT NULL UNIQUE,
      fecha_emision DATE NOT NULL,
      calibracion_id BIGINT NOT NULL
);