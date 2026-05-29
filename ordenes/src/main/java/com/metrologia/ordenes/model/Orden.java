package com.metrologia.ordenes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "ordenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_orden", nullable = false, unique = true)
    private String numeroOrden;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false)
    private String estado;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "instrumento_id", nullable = false)
    private Long instrumentoId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
}