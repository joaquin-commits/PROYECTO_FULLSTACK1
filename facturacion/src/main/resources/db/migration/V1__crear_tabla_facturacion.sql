CREATE TABLE facturacion (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     numero_factura VARCHAR(50) NOT NULL UNIQUE,
     fecha_emision DATE NOT NULL,
     monto_total DECIMAL(10,2) NOT NULL,
     orden_id BIGINT NOT NULL,
     cliente_id BIGINT NOT NULL
);