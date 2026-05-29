package com.metrologia.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteResponseDTO {
    private Long id;
    private String nombre;
    private String documento;
    private String email;
    private String telefono;
}