package com.metrologia.instrumentos.service;

import com.metrologia.instrumentos.client.LaboratorioClient;
import com.metrologia.instrumentos.dto.InstrumentoRequestDTO;
import com.metrologia.instrumentos.dto.InstrumentoResponseDTO;
import com.metrologia.instrumentos.dto.LaboratorioDTO;
import com.metrologia.instrumentos.model.Instrumento;
import com.metrologia.instrumentos.repository.InstrumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstrumentoService {

    private final InstrumentoRepository repository;
    private final LaboratorioClient laboratorioClient;

    public InstrumentoResponseDTO guardar(InstrumentoRequestDTO dto) {

        // Primero valida que el laboratorio exista.
        // Así no queda guardado un instrumento con laboratorio inexistente.
        LaboratorioDTO lab = laboratorioClient.obtenerLaboratorio(dto.getLaboratorioId());

        Instrumento instrumento = new Instrumento(
                null,
                dto.getNombre(),
                dto.getMarca(),
                dto.getLaboratorioId()
        );

        Instrumento guardado = repository.save(instrumento);

        return new InstrumentoResponseDTO(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getMarca(),
                lab.getNombre()
        );
    }

    public List<InstrumentoResponseDTO> obtenerTodos() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public InstrumentoResponseDTO obtenerPorId(Long id) {
        Instrumento instrumento = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Instrumento no encontrado con id: " + id
                ));

        return mapToDTO(instrumento);
    }
    public InstrumentoResponseDTO actualizar(Long id, InstrumentoRequestDTO dto) {
        Instrumento instrumento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instrumento no encontrado"));

        instrumento.setNombre(dto.getNombre());
        instrumento.setMarca(dto.getMarca());
        instrumento.setLaboratorioId(dto.getLaboratorioId());

        Instrumento actualizado = repository.save(instrumento);
        return mapToDTO(actualizado);
    }
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Instrumento no encontrado");
        }
        repository.deleteById(id);
    }

    private InstrumentoResponseDTO mapToDTO(Instrumento instrumento) {
        LaboratorioDTO lab = laboratorioClient.obtenerLaboratorio(instrumento.getLaboratorioId());

        return new InstrumentoResponseDTO(
                instrumento.getId(),
                instrumento.getNombre(),
                instrumento.getMarca(),
                lab.getNombre()
        );
    }
}