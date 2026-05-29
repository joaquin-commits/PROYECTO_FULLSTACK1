package com.metrologia.ordenes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdenRequestDTO {
    @NotBlank(message = "El número de orden es obligatorio")
    private String numeroOrden;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El ID del instrumento es obligatorio")
    private Long instrumentoId;

    @NotNull(message = "El ID del usuario que recibe es obligatorio")
    private Long usuarioId;
}