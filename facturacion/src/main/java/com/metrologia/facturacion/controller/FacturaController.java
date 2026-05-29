package com.metrologia.facturacion.controller;

import com.metrologia.facturacion.dto.FacturaRequestDTO;
import com.metrologia.facturacion.dto.FacturaResponseDTO;
import com.metrologia.facturacion.service.FacturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaService service;

    @PostMapping
    public ResponseEntity<FacturaResponseDTO> crear(@Valid @RequestBody FacturaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<FacturaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    @PutMapping("/{id}")
    public ResponseEntity<FacturaResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody FacturaRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}