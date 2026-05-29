package com.metrologia.laboratorios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LaboratorioResponseDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String acreditacion;
}