package com.metrologia.laboratorios.service;

import com.metrologia.laboratorios.dto.LaboratorioRequestDTO;
import com.metrologia.laboratorios.dto.LaboratorioResponseDTO;
import com.metrologia.laboratorios.model.Laboratorio;
import com.metrologia.laboratorios.repository.LaboratorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LaboratorioService {

    private final LaboratorioRepository repository;

    public LaboratorioResponseDTO crear(LaboratorioRequestDTO dto) {
        Laboratorio lab = new Laboratorio(
                null,
                dto.getNombre(),
                dto.getDireccion(),
                dto.getAcreditacion()
        );

        Laboratorio guardado = repository.save(lab);

        return mapToDTO(guardado);
    }

    public List<LaboratorioResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
    public LaboratorioResponseDTO actualizar(Long id, LaboratorioRequestDTO dto) {

        Laboratorio laboratorio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laboratorio no encontrado"));


        laboratorio.setNombre(dto.getNombre());
        laboratorio.setDireccion(dto.getDireccion());
        laboratorio.setAcreditacion(dto.getAcreditacion());


        Laboratorio actualizado = repository.save(laboratorio);
        return new LaboratorioResponseDTO(actualizado.getId(), actualizado.getNombre(), actualizado.getDireccion(), actualizado.getAcreditacion());
    }

    public LaboratorioResponseDTO obtenerPorId(Long id) {
        Laboratorio lab = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Laboratorio no encontrado con id: " + id
                ));

        return mapToDTO(lab);
    }
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Laboratorio no encontrado");
        }
        repository.deleteById(id);
    }

    private LaboratorioResponseDTO mapToDTO(Laboratorio lab) {
        return new LaboratorioResponseDTO(
                lab.getId(),
                lab.getNombre(),
                lab.getDireccion(),
                lab.getAcreditacion()
        );
    }
}
