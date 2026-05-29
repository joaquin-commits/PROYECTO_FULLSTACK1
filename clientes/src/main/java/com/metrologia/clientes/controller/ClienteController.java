package com.metrologia.clientes.controller;

import com.metrologia.clientes.dto.ClienteRequestDTO;
import com.metrologia.clientes.dto.ClienteResponseDTO;
import com.metrologia.clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crear(@Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}