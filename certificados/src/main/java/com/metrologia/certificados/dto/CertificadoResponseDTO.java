package com.metrologia.certificados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CertificadoResponseDTO {
    private Long id;
    private String numeroCertificado;
    private LocalDate fechaEmision;
    private String codigoCalibracion;
    private String resultadoMedicion;
    private String nombreInstrumento;
    private String tecnicoAsignado;
}