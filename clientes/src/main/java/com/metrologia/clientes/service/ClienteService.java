package com.metrologia.clientes.service;

import com.metrologia.clientes.dto.ClienteRequestDTO;
import com.metrologia.clientes.dto.ClienteResponseDTO;
import com.metrologia.clientes.model.Cliente;
import com.metrologia.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteResponseDTO crear(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente(null, dto.getNombre(), dto.getDocumento(), dto.getEmail(), dto.getTelefono());
        Cliente guardado = repository.save(cliente);
        return mapToDTO(guardado);
    }

    public List<ClienteResponseDTO> listar() {
        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    public ClienteResponseDTO obtenerPorId(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return mapToDTO(cliente);
    }
    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(dto.getNombre());
        cliente.setDocumento(dto.getDocumento());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());

        Cliente actualizado = repository.save(cliente);
        return mapToDTO(actualizado);
    }
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        repository.deleteById(id);
    }

    private ClienteResponseDTO mapToDTO(Cliente cliente) {
        return new ClienteResponseDTO(cliente.getId(), cliente.getNombre(), cliente.getDocumento(), cliente.getEmail(), cliente.getTelefono());
    }

}