package com.metrologia.calibracion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CalibracionResponseDTO {
    private Long id;
    private String codigoCalibracion;
    private LocalDate fechaCalibracion;
    private String resultadoMedicion;
    private String numeroOrden;
    private String nombreInstrumento;
    private String codigoPatron;
    private String nombreTecnico;
}