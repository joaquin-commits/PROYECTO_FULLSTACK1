package com.metrologia.calibracion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "calibracion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calibracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_calibracion", nullable = false, unique = true)
    private String codigoCalibracion;

    @Column(name = "fecha_calibracion", nullable = false)
    private LocalDate fechaCalibracion;

    @Column(name = "resultado_medicion", nullable = false)
    private String resultadoMedicion;

    @Column(name = "orden_id", nullable = false)
    private Long ordenId;

    @Column(name = "instrumento_id", nullable = false)
    private Long instrumentoId;

    @Column(name = "patron_id", nullable = false)
    private Long patronId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
}