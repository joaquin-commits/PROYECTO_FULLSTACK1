package com.metrologia.instrumentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InstrumentoRequestDTO {
    @NotBlank
    private String nombre;
    @NotBlank
    private String marca;
    @NotNull
    private Long laboratorioId;
}