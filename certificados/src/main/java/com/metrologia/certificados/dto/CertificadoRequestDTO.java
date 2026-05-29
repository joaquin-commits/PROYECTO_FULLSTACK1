package com.metrologia.certificados.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CertificadoRequestDTO {
    @NotNull(message = "El ID de la calibración es obligatorio")
    private Long calibracionId;
}