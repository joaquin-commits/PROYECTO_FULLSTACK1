package com.metrologia.calibracion.client;

import com.metrologia.calibracion.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MetrologiaClient {
    private final WebClient.Builder webClientBuilder;

    public OrdenDTO obtenerOrden(Long id) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8096/api/ordenes/{id}", id)
                .retrieve().bodyToMono(OrdenDTO.class).block();
    }

    public InstrumentoDTO obtenerInstrumento(Long id) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8092/api/instrumentos/{id}", id)
                .retrieve().bodyToMono(InstrumentoDTO.class).block();
    }

    public PatronDTO obtenerPatron(Long id) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8095/api/patrones/{id}", id)
                .retrieve().bodyToMono(PatronDTO.class).block();
    }

    public UsuarioDTO obtenerUsuario(Long id) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8094/api/usuarios/{id}", id)
                .retrieve().bodyToMono(UsuarioDTO.class).block();
    }
}