package com.metrologia.ordenes.client;

import com.metrologia.ordenes.dto.ClienteDTO;
import com.metrologia.ordenes.dto.InstrumentoDTO;
import com.metrologia.ordenes.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MetrologiaClient {
    private final WebClient.Builder webClientBuilder;

    public ClienteDTO obtenerCliente(Long id) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8093/api/clientes/{id}", id)
                .retrieve().bodyToMono(ClienteDTO.class).block();
    }

    public InstrumentoDTO obtenerInstrumento(Long id) {
        // Asumiendo que crearás luego el método obtener por ID en instrumentos
        return webClientBuilder.build().get()
                .uri("http://localhost:8092/api/instrumentos/{id}", id)
                .retrieve().bodyToMono(InstrumentoDTO.class).block();
    }

    public UsuarioDTO obtenerUsuario(Long id) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8094/api/usuarios/{id}", id)
                .retrieve().bodyToMono(UsuarioDTO.class).block();
    }
}