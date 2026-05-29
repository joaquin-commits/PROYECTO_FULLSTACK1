package com.metrologia.laboratorios.controller;

import com.metrologia.laboratorios.dto.LaboratorioRequestDTO;
import com.metrologia.laboratorios.dto.LaboratorioResponseDTO;
import com.metrologia.laboratorios.service.LaboratorioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
@RequiredArgsConstructor
public class LaboratorioController {

    private final LaboratorioService service;

    @PostMapping
    public ResponseEntity<LaboratorioResponseDTO> crear(@Valid @RequestBody LaboratorioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<LaboratorioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<LaboratorioResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody LaboratorioRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}