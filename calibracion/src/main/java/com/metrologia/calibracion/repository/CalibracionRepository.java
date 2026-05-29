package com.metrologia.calibracion.repository;

import com.metrologia.calibracion.model.Calibracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalibracionRepository extends JpaRepository<Calibracion, Long> {
}