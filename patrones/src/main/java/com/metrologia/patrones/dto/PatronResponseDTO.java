package com.metrologia.patrones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatronResponseDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String marca;
    private String nombreLaboratorio; // Dato que obtenemos vía WebClient
}