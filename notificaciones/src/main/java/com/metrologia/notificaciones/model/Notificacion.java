package com.metrologia.notificaciones.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_destino", nullable = false)
    private String emailDestino;

    @Column(nullable = false)
    private String asunto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDate fechaEnvio;
}