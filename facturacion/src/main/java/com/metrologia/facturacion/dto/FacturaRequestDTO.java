package com.metrologia.facturacion.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class FacturaRequestDTO {
    @NotNull(message = "El monto total es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal montoTotal;

    @NotNull(message = "El ID de la orden es obligatorio")
    private Long ordenId;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;
}