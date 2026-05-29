package com.metrologia.notificaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NotificacionResponseDTO {
    private Long id;
    private String emailDestino;
    private String asunto;
    private String mensaje;
    private LocalDate fechaEnvio;
}