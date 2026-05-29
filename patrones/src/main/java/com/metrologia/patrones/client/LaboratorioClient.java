package com.metrologia.patrones.client;

import com.metrologia.patrones.dto.LaboratorioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LaboratorioClient {
    private final WebClient webClient;

    public LaboratorioDTO obtenerLaboratorio(Long id) {
        return webClient.get().uri("/api/laboratorios/{id}", id)
                .retrieve()
                .bodyToMono(LaboratorioDTO.class)
                .block();
    }
}