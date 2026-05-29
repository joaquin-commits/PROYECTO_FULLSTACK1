package com.metrologia.certificados.service;

import com.metrologia.certificados.client.MetrologiaClient;
import com.metrologia.certificados.dto.CalibracionDTO;
import com.metrologia.certificados.dto.CertificadoRequestDTO;
import com.metrologia.certificados.dto.CertificadoResponseDTO;
import com.metrologia.certificados.model.Certificado;
import com.metrologia.certificados.repository.CertificadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificadoService {
    private final CertificadoRepository repository;
    private final MetrologiaClient metrologiaClient;

    public CertificadoResponseDTO crear(CertificadoRequestDTO dto) {
        // Generamos un número de certificado único y profesional
        String numeroCertificado = "CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Certificado certificado = new Certificado(
                null, numeroCertificado, LocalDate.now(), dto.getCalibracionId()
        );
        return mapToDTO(repository.save(certificado));
    }

    public List<CertificadoResponseDTO> listar() {
        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    public CertificadoResponseDTO actualizar(Long id, CertificadoRequestDTO dto) {
        Certificado certificado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificado no encontrado"));

        certificado.setCalibracionId(dto.getCalibracionId());

        Certificado actualizado = repository.save(certificado);
        return mapToDTO(actualizado);
    }
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Certificado no encontrado");
        }
        repository.deleteById(id);
    }

    private CertificadoResponseDTO mapToDTO(Certificado certificado) {
        CalibracionDTO calibracion = metrologiaClient.obtenerCalibracion(certificado.getCalibracionId());

        return new CertificadoResponseDTO(
                certificado.getId(),
                certificado.getNumeroCertificado(),
                certificado.getFechaEmision(),
                calibracion.getCodigoCalibracion(),
                calibracion.getResultadoMedicion(),
                calibracion.getNombreInstrumento(),
                calibracion.getNombreTecnico()
        );
    }
}