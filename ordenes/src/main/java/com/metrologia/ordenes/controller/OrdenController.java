package com.metrologia.ordenes.controller;

import com.metrologia.ordenes.dto.OrdenRequestDTO;
import com.metrologia.ordenes.dto.OrdenResponseDTO;
import com.metrologia.ordenes.service.OrdenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
public class OrdenController {
    private final OrdenService service;

    @PostMapping
    public ResponseEntity<OrdenResponseDTO> crear(@Valid @RequestBody OrdenRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<OrdenResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrdenResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrdenResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody OrdenRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}