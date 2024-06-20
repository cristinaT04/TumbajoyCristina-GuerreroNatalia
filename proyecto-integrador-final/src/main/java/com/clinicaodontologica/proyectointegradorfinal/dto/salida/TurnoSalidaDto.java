package com.clinicaodontologica.proyectointegradorfinal.dto.salida;

import com.clinicaodontologica.proyectointegradorfinal.entity.Odontologo;
import com.clinicaodontologica.proyectointegradorfinal.entity.Paciente;

import java.time.LocalDateTime;

public class TurnoSalidaDto {

    private Long id;
    private LocalDateTime fechaYHora;
    private Long odontologoId;
    private Long pacienteId;

    public TurnoSalidaDto() {
    }

    public TurnoSalidaDto(Long id, LocalDateTime fechaYHora, Long odontologoId, Long pacienteId) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}
