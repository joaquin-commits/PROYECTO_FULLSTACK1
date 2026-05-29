package com.metrologia.facturacion.client;

import com.metrologia.facturacion.dto.ClienteDTO;
import com.metrologia.facturacion.dto.OrdenDTO;
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

    public ClienteDTO obtenerCliente(Long id) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8093/api/clientes/{id}", id)
                .retrieve().bodyToMono(ClienteDTO.class).block();
    }
}