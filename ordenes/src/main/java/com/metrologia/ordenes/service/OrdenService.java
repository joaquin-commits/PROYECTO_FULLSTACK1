package com.metrologia.ordenes.service;

import com.metrologia.ordenes.client.MetrologiaClient;
import com.metrologia.ordenes.dto.*;
import com.metrologia.ordenes.model.Orden;
import com.metrologia.ordenes.repository.OrdenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {
    private final OrdenRepository repository;
    private final MetrologiaClient metrologiaClient;

    public OrdenResponseDTO crear(OrdenRequestDTO dto) {
        Orden orden = new Orden(null, dto.getNumeroOrden(), LocalDate.now(), "INGRESADO",
                dto.getClienteId(), dto.getInstrumentoId(), dto.getUsuarioId());
        return mapToDTO(repository.save(orden));
    }

    public List<OrdenResponseDTO> listar() {
        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Orden no encontrada");
        }
        repository.deleteById(id);
    }

    private OrdenResponseDTO mapToDTO(Orden orden) {
        ClienteDTO cliente = metrologiaClient.obtenerCliente(orden.getClienteId());
        InstrumentoDTO instrumento = metrologiaClient.obtenerInstrumento(orden.getInstrumentoId());
        UsuarioDTO usuario = metrologiaClient.obtenerUsuario(orden.getUsuarioId());

        return new OrdenResponseDTO(
                orden.getId(),
                orden.getNumeroOrden(),
                orden.getFechaIngreso(),
                orden.getEstado(),
                cliente.getNombre(),
                instrumento.getNombre() + " (" + instrumento.getMarca() + ")",
                usuario.getNombre()
        );
    }
    public OrdenResponseDTO actualizar(Long id, OrdenRequestDTO dto) {
        Orden orden = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        orden.setNumeroOrden(dto.getNumeroOrden());
        orden.setClienteId(dto.getClienteId());
        orden.setInstrumentoId(dto.getInstrumentoId());
        orden.setUsuarioId(dto.getUsuarioId());

        Orden actualizado = repository.save(orden);
        return mapToDTO(actualizado);
    }

    public OrdenResponseDTO obtenerPorId(Long id) {
        Orden orden = repository.findById(id).orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        return mapToDTO(orden);
    }
}