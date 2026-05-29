package com.metrologia.instrumentos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InstrumentoResponseDTO {
    private Long id;
    private String nombre;
    private String marca;
    private String nombreLaboratorio;
}