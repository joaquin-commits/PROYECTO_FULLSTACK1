package com.metrologia.usuarios.service;

import com.metrologia.usuarios.dto.UsuarioRequestDTO;
import com.metrologia.usuarios.dto.UsuarioResponseDTO;
import com.metrologia.usuarios.model.Usuario;
import com.metrologia.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioResponseDTO crear(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario(null, dto.getNombre(), dto.getEmail(), dto.getPassword(), dto.getRol());
        Usuario guardado = repository.save(usuario);
        return mapToDTO(guardado);
    }

    public List<UsuarioResponseDTO> listar() {
        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    public UsuarioResponseDTO obtenerPorId(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapToDTO(usuario);
    }
    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(dto.getRol());

        Usuario actualizado = repository.save(usuario);
        return mapToDTO(actualizado);
    }
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        repository.deleteById(id);
    }

    private UsuarioResponseDTO mapToDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getRol());
    }
}