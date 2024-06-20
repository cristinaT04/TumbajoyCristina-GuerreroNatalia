package com.clinicaodontologica.proyectointegradorfinal.service.impl;

import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.DomicilioEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.OdontologoEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.PacienteEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.TurnoEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.TurnoSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.entity.Odontologo;
import com.clinicaodontologica.proyectointegradorfinal.entity.Paciente;
import com.clinicaodontologica.proyectointegradorfinal.repository.TurnoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    private PacienteService pacienteService;

    private OdontologoService odontologoService;

    @Test
    public void deberiaRegistrarUnTurno(){
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();

        TurnoEntradaDto turno = new TurnoEntradaDto(LocalDateTime.now(),odontologo.getId(),paciente.getId());

        TurnoSalidaDto turnoRegistrado = turnoService.registrarTurno(turno);

        assertNotNull(turnoRegistrado.getFechaYHora());
        assertNotNull(turnoRegistrado.getOdontologoId());


    }

}