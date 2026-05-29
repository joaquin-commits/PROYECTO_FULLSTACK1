package com.metrologia.certificados.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CalibracionDTO {
    private Long id;
    private String codigoCalibracion;
    private LocalDate fechaCalibracion;
    private String resultadoMedicion;
    private String nombreInstrumento;
    private String nombreTecnico;
}