package com.clinicaodontologica.proyectointegradorfinal.service.impl;

import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.OdontologoEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.OdontologoSalidaDto;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private OdontologoService odontologoService;
    @Test
    public void deberiaRegistrarseUnOdontologo(){

        OdontologoEntradaDto odontologo = new OdontologoEntradaDto("50003", "Lucia","Lopez");

        OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoRegistrado.getMatricula());
        assertNotNull(odontologoRegistrado.getId());

    }
    @Test
    public void deberiaListarTodosLosOdontologosRegistrados() {

        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }

}
