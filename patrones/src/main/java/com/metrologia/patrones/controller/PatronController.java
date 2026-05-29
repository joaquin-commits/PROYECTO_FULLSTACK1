package com.metrologia.patrones.controller;

import com.metrologia.patrones.dto.PatronRequestDTO;
import com.metrologia.patrones.dto.PatronResponseDTO;
import com.metrologia.patrones.service.PatronService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrones")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService service;

    @PostMapping
    public ResponseEntity<PatronResponseDTO> crear(@Valid @RequestBody PatronRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<PatronResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatronResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PatronRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}