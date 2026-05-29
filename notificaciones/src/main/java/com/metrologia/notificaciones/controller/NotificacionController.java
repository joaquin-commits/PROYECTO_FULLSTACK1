package com.metrologia.notificaciones.controller;

import com.metrologia.notificaciones.dto.NotificacionRequestDTO;
import com.metrologia.notificaciones.dto.NotificacionResponseDTO;
import com.metrologia.notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService service;

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crear(@Valid @RequestBody NotificacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody NotificacionRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}