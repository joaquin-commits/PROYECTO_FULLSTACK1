package com.metrologia.notificaciones.service;

import com.metrologia.notificaciones.dto.NotificacionRequestDTO;
import com.metrologia.notificaciones.dto.NotificacionResponseDTO;
import com.metrologia.notificaciones.model.Notificacion;
import com.metrologia.notificaciones.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {
    private final NotificacionRepository repository;

    public NotificacionResponseDTO crear(NotificacionRequestDTO dto) {
        Notificacion notificacion = new Notificacion(
                null, dto.getEmailDestino(), dto.getAsunto(), dto.getMensaje(), LocalDate.now()
        );

        // Simulación de envío de correo en la consola
        System.out.println("=========================================");
        System.out.println("ENVIANDO CORREO A: " + dto.getEmailDestino());
        System.out.println("ASUNTO: " + dto.getAsunto());
        System.out.println("MENSAJE: " + dto.getMensaje());
        System.out.println("=========================================");

        Notificacion guardado = repository.save(notificacion);
        return new NotificacionResponseDTO(
                guardado.getId(), guardado.getEmailDestino(), guardado.getAsunto(),
                guardado.getMensaje(), guardado.getFechaEnvio()
        );
    }

    public List<NotificacionResponseDTO> listar() {
        return repository.findAll().stream().map(n -> new NotificacionResponseDTO(
                n.getId(), n.getEmailDestino(), n.getAsunto(), n.getMensaje(), n.getFechaEnvio()
        )).toList();
    }
    public NotificacionResponseDTO actualizar(Long id, NotificacionRequestDTO dto) {
        Notificacion notificacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        notificacion.setEmailDestino(dto.getEmailDestino());
        notificacion.setAsunto(dto.getAsunto());
        notificacion.setMensaje(dto.getMensaje());

        Notificacion actualizado = repository.save(notificacion);
        return new NotificacionResponseDTO(
                actualizado.getId(), actualizado.getEmailDestino(), actualizado.getAsunto(),
                actualizado.getMensaje(), actualizado.getFechaEnvio()
        );
    }
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Notificación no encontrada");
        }
        repository.deleteById(id);
    }
}