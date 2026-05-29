package com.metrologia.instrumentos.controller;

import com.metrologia.instrumentos.dto.InstrumentoRequestDTO;
import com.metrologia.instrumentos.dto.InstrumentoResponseDTO;
import com.metrologia.instrumentos.service.InstrumentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instrumentos")
@RequiredArgsConstructor
public class InstrumentoController {

    private final InstrumentoService service;

    @PostMapping
    public ResponseEntity<InstrumentoResponseDTO> crear(@Valid @RequestBody InstrumentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<InstrumentoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<InstrumentoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody InstrumentoRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}