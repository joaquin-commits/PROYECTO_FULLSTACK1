package com.metrologia.calibracion.controller;

import com.metrologia.calibracion.dto.CalibracionRequestDTO;
import com.metrologia.calibracion.dto.CalibracionResponseDTO;
import com.metrologia.calibracion.service.CalibracionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/calibraciones")
@RequiredArgsConstructor
public class CalibracionController {
    private final CalibracionService service;

    @PostMapping
    public ResponseEntity<CalibracionResponseDTO> crear(@Valid @RequestBody CalibracionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<CalibracionResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CalibracionResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CalibracionResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CalibracionRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}