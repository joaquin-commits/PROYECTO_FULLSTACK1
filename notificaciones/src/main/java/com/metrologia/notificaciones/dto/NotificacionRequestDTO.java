package com.metrologia.notificaciones.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificacionRequestDTO {
    @NotBlank(message = "El email de destino es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String emailDestino;

    @NotBlank(message = "El asunto es obligatorio")
    private String asunto;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;
}