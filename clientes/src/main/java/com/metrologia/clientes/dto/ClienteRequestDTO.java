package com.metrologia.clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El documento es obligatorio")
    private String documento;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String email;

    private String telefono;
}