package com.metrologia.laboratorios.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LaboratorioRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "La acreditación es obligatoria")
    private String acreditacion;
}