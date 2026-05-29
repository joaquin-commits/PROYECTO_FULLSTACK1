package com.metrologia.patrones.service;

import com.metrologia.patrones.client.LaboratorioClient;
import com.metrologia.patrones.dto.LaboratorioDTO;
import com.metrologia.patrones.dto.PatronRequestDTO;
import com.metrologia.patrones.dto.PatronResponseDTO;
import com.metrologia.patrones.model.Patron;
import com.metrologia.patrones.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatronService {

    private final PatronRepository repository;
    private final LaboratorioClient laboratorioClient;

    public PatronResponseDTO crear(PatronRequestDTO dto) {

        // Primero valida que el laboratorio exista.
        LaboratorioDTO lab = laboratorioClient.obtenerLaboratorio(dto.getLaboratorioId());

        Patron patron = new Patron(
                null,
                dto.getCodigo(),
                dto.getNombre(),
                dto.getMarca(),
                dto.getLaboratorioId()
        );

        Patron guardado = repository.save(patron);

        return new PatronResponseDTO(
                guardado.getId(),
                guardado.getCodigo(),
                guardado.getNombre(),
                guardado.getMarca(),
                lab.getNombre()
        );
    }

    public List<PatronResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public PatronResponseDTO obtenerPorId(Long id) {
        Patron patron = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Patrón no encontrado con id: " + id
                ));

        return mapToDTO(patron);
    }
    public PatronResponseDTO actualizar(Long id, PatronRequestDTO dto) {
        Patron patron = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patrón no encontrado"));

        patron.setCodigo(dto.getCodigo());
        patron.setNombre(dto.getNombre());
        patron.setMarca(dto.getMarca());
        patron.setLaboratorioId(dto.getLaboratorioId());

        Patron actualizado = repository.save(patron);
        return mapToDTO(actualizado);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Patrón no encontrado");
        }
        repository.deleteById(id);
    }
    private PatronResponseDTO mapToDTO(Patron patron) {
        LaboratorioDTO lab = laboratorioClient.obtenerLaboratorio(patron.getLaboratorioId());

        return new PatronResponseDTO(
                patron.getId(),
                patron.getCodigo(),
                patron.getNombre(),
                patron.getMarca(),
                lab.getNombre()
        );
    }
}