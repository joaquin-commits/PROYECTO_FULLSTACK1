package com.metrologia.calibracion.service;

import com.metrologia.calibracion.client.MetrologiaClient;
import com.metrologia.calibracion.dto.*;
import com.metrologia.calibracion.model.Calibracion;
import com.metrologia.calibracion.repository.CalibracionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalibracionService {
    private final CalibracionRepository repository;
    private final MetrologiaClient metrologiaClient;

    public CalibracionResponseDTO crear(CalibracionRequestDTO dto) {
        Calibracion calibracion = new Calibracion(
                null, dto.getCodigoCalibracion(), LocalDate.now(), dto.getResultadoMedicion(),
                dto.getOrdenId(), dto.getInstrumentoId(), dto.getPatronId(), dto.getUsuarioId()
        );
        return mapToDTO(repository.save(calibracion));
    }

    public List<CalibracionResponseDTO> listar() {
        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    public CalibracionResponseDTO actualizar(Long id, CalibracionRequestDTO dto) {
        Calibracion calibracion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calibración no encontrada"));

        calibracion.setCodigoCalibracion(dto.getCodigoCalibracion());
        calibracion.setResultadoMedicion(dto.getResultadoMedicion());
        calibracion.setOrdenId(dto.getOrdenId());
        calibracion.setInstrumentoId(dto.getInstrumentoId());
        calibracion.setPatronId(dto.getPatronId());
        calibracion.setUsuarioId(dto.getUsuarioId());

        Calibracion actualizado = repository.save(calibracion);
        return mapToDTO(actualizado);
    }

    private CalibracionResponseDTO mapToDTO(Calibracion calibracion) {
        OrdenDTO orden = metrologiaClient.obtenerOrden(calibracion.getOrdenId());
        InstrumentoDTO instrumento = metrologiaClient.obtenerInstrumento(calibracion.getInstrumentoId());
        PatronDTO patron = metrologiaClient.obtenerPatron(calibracion.getPatronId());
        UsuarioDTO usuario = metrologiaClient.obtenerUsuario(calibracion.getUsuarioId());

        return new CalibracionResponseDTO(
                calibracion.getId(),
                calibracion.getCodigoCalibracion(),
                calibracion.getFechaCalibracion(),
                calibracion.getResultadoMedicion(),
                orden.getNumeroOrden(),
                instrumento.getNombre(),
                patron.getCodigo() + " - " + patron.getNombre(),
                usuario.getNombre()
        );
    }
    public CalibracionResponseDTO obtenerPorId(Long id) {
        Calibracion calibracion = repository.findById(id).orElseThrow(() -> new RuntimeException("Calibración no encontrada"));
        return mapToDTO(calibracion);
    }
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Calibración no encontrada");
        }
        repository.deleteById(id);
    }
}