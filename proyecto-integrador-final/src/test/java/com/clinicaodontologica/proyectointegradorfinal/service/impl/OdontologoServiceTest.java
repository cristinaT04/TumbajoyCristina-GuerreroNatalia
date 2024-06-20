package com.clinicaodontologica.proyectointegradorfinal.service.impl;

import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.OdontologoEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.OdontologoSalidaDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    @Test
    @Order(1)
    public void deberiaRegistrarseUnOdontologo(){

        OdontologoEntradaDto odontologo = new OdontologoEntradaDto("50003", "Lucia","Lopez");

        OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoRegistrado.getMatricula());
        assertNotNull(odontologoRegistrado.getId());

    }

    @Test
    @Order(2)
    void deberiaEliminarseElOdontologoConId1(){

        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(3)
    public void deberiaDevolverUnaListaVaciaDeTodosLosOdontologosRegistrados() {
        List<OdontologoSalidaDto> listadoDeOdontologo = odontologoService.listarOdontologos();
        assertTrue(listadoDeOdontologo.isEmpty());
    }

}
