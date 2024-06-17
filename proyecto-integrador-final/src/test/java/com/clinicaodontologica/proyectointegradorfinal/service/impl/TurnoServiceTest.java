package com.clinicaodontologica.proyectointegradorfinal.service.impl;

import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.DomicilioEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.OdontologoEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.PacienteEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.TurnoEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.TurnoSalidaDto;
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

    @Test
    public void deberiaRegistrarUnTurno(){
        OdontologoEntradaDto odontologo = new OdontologoEntradaDto("50003", "Lucia","Lopez");
        PacienteEntradaDto paciente = new PacienteEntradaDto("Maria", "Lopez", 523458,LocalDate.of(2024,6,13),new DomicilioEntradaDto("calle 21",25,"Torres", "Local"));

        TurnoEntradaDto turno = new TurnoEntradaDto(LocalDateTime.now(),odontologo,paciente);

        TurnoSalidaDto turnoRegistrado = turnoService.registrarTurno(turno);

        assertNotNull(turnoRegistrado.getFechaYHora());
        assertNotNull(turnoRegistrado.getOdontologoSalidaDto().getId());


    }

}