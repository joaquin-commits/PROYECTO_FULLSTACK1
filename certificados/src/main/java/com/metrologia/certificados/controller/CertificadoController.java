package com.metrologia.certificados.controller;

import com.metrologia.certificados.dto.CertificadoRequestDTO;
import com.metrologia.certificados.dto.CertificadoResponseDTO;
import com.metrologia.certificados.service.CertificadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/certificados")
@RequiredArgsConstructor
public class CertificadoController {
    private final CertificadoService service;

    @PostMapping
    public ResponseEntity<CertificadoResponseDTO> crear(@Valid @RequestBody CertificadoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<CertificadoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
    @PutMapping("/{id}")
    public ResponseEntity<CertificadoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CertificadoRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}