package com.metrologia.calibracion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CalibracionRequestDTO {
    @NotBlank(message = "El código de calibración es obligatorio")
    private String codigoCalibracion;

    @NotBlank(message = "Los resultados son obligatorios")
    private String resultadoMedicion;

    @NotNull(message = "La orden es obligatoria")
    private Long ordenId;

    @NotNull(message = "El instrumento es obligatorio")
    private Long instrumentoId;

    @NotNull(message = "El patrón es obligatorio")
    private Long patronId;

    @NotNull(message = "El usuario técnico es obligatorio")
    private Long usuarioId;
}