CREATE TABLE notificaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email_destino VARCHAR(255) NOT NULL,
    asunto VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_envio DATE NOT NULL
);