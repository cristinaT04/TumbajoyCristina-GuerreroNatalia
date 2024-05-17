package com.backend.test;

import com.backend.entity.Odontologo;
import com.backend.repository.impl.OdontologoDaoH2;
import com.backend.service.impl.OdontologoService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoServiceTest {

    private OdontologoService odontologoService;
    @Test
    public void deberiaRegistrarseUnOdontologo(){

        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo("50003", "Lucia","Lopez");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoRegistrado.getMatricula());
        assertNotNull(odontologoRegistrado.getId());

    }
    @Test
    public void deberiaListarTodosLosOdontologosRegistrados() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }
}
