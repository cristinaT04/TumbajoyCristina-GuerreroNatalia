package com.clinicaodontologica.proyectointegradorfinal.service;

import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.PacienteEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.PacienteSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.entity.Paciente;
import com.clinicaodontologica.proyectointegradorfinal.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto);
    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto buscarPacientePorId(Long id);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto actualizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id);
}
