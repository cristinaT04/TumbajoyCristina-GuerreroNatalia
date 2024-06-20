package com.clinicaodontologica.proyectointegradorfinal.service.impl;

import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.DomicilioEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.PacienteEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.PacienteSalidaDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;


    @Test
    @Order(1)
    public void deberiaRegistrarUnPaciente() {
        DomicilioEntradaDto domicilio = new DomicilioEntradaDto("Calle 40", 123, "Springfield", "Springfield");
        PacienteEntradaDto paciente = new PacienteEntradaDto("Homero", "lopez", 12345678, LocalDate.now(), domicilio);

        PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(paciente);

        assertNotNull(pacienteRegistrado.getId());
        assertEquals("Homero", pacienteRegistrado.getNombre());
        assertEquals("lopez", pacienteRegistrado.getApellido());
        assertEquals(12345678, pacienteRegistrado.getDni());
        assertNotNull(pacienteRegistrado.getFechaIngreso());
        assertNotNull(pacienteRegistrado.getDomicilioSalidaDto());
    }


    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDePacientes(){
        List<PacienteSalidaDto> listadoDePacientes = pacienteService.listarPacientes();
        assertFalse(listadoDePacientes.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElPacienteConId1(){

        assertDoesNotThrow(() -> pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(4)
    void deberiaDevolverUnaListaVaciaDePacientes(){
        List<PacienteSalidaDto> listadoDePacientes = pacienteService.listarPacientes();
        assertTrue(listadoDePacientes.isEmpty());
    }
}