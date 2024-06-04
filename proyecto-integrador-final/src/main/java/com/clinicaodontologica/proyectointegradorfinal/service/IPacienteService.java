package com.clinicaodontologica.proyectointegradorfinal.service;

import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.PacienteEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.PacienteSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto);
    List<PacienteSalidaDto> listarPacientes();
}
