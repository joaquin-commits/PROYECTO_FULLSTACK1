package com.metrologia.ordenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrdenResponseDTO {
    private Long id;
    private String numeroOrden;
    private LocalDate fechaIngreso;
    private String estado;
    private String nombreCliente;
    private String nombreInstrumento;
    private String nombreRecepcionista;
}