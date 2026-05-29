package com.metrologia.certificados.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "certificados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certificado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_certificado", nullable = false, unique = true)
    private String numeroCertificado;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "calibracion_id", nullable = false)
    private Long calibracionId;
}