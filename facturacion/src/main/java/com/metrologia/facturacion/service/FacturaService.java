package com.metrologia.facturacion.service;

import com.metrologia.facturacion.client.MetrologiaClient;
import com.metrologia.facturacion.dto.ClienteDTO;
import com.metrologia.facturacion.dto.FacturaRequestDTO;
import com.metrologia.facturacion.dto.FacturaResponseDTO;
import com.metrologia.facturacion.dto.OrdenDTO;
import com.metrologia.facturacion.model.Factura;
import com.metrologia.facturacion.repository.FacturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacturaService {
    private final FacturaRepository repository;
    private final MetrologiaClient metrologiaClient;

    public FacturaResponseDTO crear(FacturaRequestDTO dto) {
        // Generamos un folio de factura único
        String numeroFactura = "FAC-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        Factura factura = new Factura(
                null, numeroFactura, LocalDate.now(), dto.getMontoTotal(),
                dto.getOrdenId(), dto.getClienteId()
        );
        return mapToDTO(repository.save(factura));
    }

    public List<FacturaResponseDTO> listar() {
        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    public FacturaResponseDTO actualizar(Long id, FacturaRequestDTO dto) {
        Factura factura = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        factura.setMontoTotal(dto.getMontoTotal());
        factura.setOrdenId(dto.getOrdenId());
        factura.setClienteId(dto.getClienteId());

        Factura actualizado = repository.save(factura);
        return mapToDTO(actualizado);
    }
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Factura no encontrada");
        }
        repository.deleteById(id);
    }

    private FacturaResponseDTO mapToDTO(Factura factura) {
        OrdenDTO orden = metrologiaClient.obtenerOrden(factura.getOrdenId());
        ClienteDTO cliente = metrologiaClient.obtenerCliente(factura.getClienteId());

        return new FacturaResponseDTO(
                factura.getId(),
                factura.getNumeroFactura(),
                factura.getFechaEmision(),
                factura.getMontoTotal(),
                orden.getNumeroOrden(),
                cliente.getNombre(),
                cliente.getDocumento()
        );
    }
}