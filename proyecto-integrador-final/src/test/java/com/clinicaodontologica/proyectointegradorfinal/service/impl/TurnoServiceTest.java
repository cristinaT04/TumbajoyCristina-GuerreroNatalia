package com.clinicaodontologica.proyectointegradorfinal.service.impl;

import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.DomicilioEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.OdontologoEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.PacienteEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.TurnoEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.OdontologoSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.PacienteSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.TurnoSalidaDto;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnTurno() {
        DomicilioEntradaDto domicilio = new DomicilioEntradaDto("Calle 40", 123, "Springfield", "Springfield");
        PacienteEntradaDto paciente = new PacienteEntradaDto("Homero", "lopez", 12345678, LocalDate.now(), domicilio);

        PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(paciente);

        OdontologoEntradaDto odontologo = new OdontologoEntradaDto("50003", "Lucia","Lopez");

        OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        PacienteSalidaDto pacienteSalidaDto = pacienteService.buscarPacientePorId(pacienteRegistrado.getId());
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(odontologoRegistrado.getId());

        TurnoEntradaDto turno = new TurnoEntradaDto(LocalDateTime.now(),1L,1L);

        TurnoSalidaDto turnoRegistrado = turnoService.registrarTurno(turno);

        assertNotNull(turnoRegistrado.getId());
        assertNotNull(turnoRegistrado.getPacienteSalidaDto());
        assertNotNull(turnoRegistrado.getOdontologoSalidaDto());

    }

    @Test
    @Order(2)
    void deberiaListarTurnos() {
        List<TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();
        assertFalse(listadoDeTurnos.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarElTurnoRegistrado() {
        assertDoesNotThrow(() -> turnoService.eliminarTurno(1L));
    }
}