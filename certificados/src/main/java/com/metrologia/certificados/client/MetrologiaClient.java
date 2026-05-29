package com.metrologia.certificados.client;

import com.metrologia.certificados.dto.CalibracionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MetrologiaClient {
    private final WebClient webClient;

    public CalibracionDTO obtenerCalibracion(Long id) {
        return webClient.get().uri("/api/calibraciones/{id}", id)
                .retrieve().bodyToMono(CalibracionDTO.class).block();
    }
}