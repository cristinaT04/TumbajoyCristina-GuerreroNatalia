package com.clinicaodontologica.proyectointegradorfinal.repository;

import com.clinicaodontologica.proyectointegradorfinal.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
