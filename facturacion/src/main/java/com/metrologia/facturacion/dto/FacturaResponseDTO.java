package com.metrologia.facturacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FacturaResponseDTO {
    private Long id;
    private String numeroFactura;
    private LocalDate fechaEmision;
    private BigDecimal montoTotal;
    private String numeroOrden;
    private String nombreCliente;
    private String documentoCliente;
}