package com.backend.test;

import com.backend.entity.Odontologo;
import com.backend.repository.impl.OdontologoDaoEnMemoria;
import com.backend.service.impl.OdontologoServiceEnMemoria;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OdontologoServiceEnMemoriaTest {

    private OdontologoServiceEnMemoria odontologoServiceEnMemoria;

    @Test
    public void deberiaRegistrarDosOdontologosYListarLosOdontologosRegistrados(){
        odontologoServiceEnMemoria = new OdontologoServiceEnMemoria(new OdontologoDaoEnMemoria());
        Odontologo odontologo1 = new Odontologo(92233720368547707L,"50001","Carlos","Mora");
        Odontologo odontologo2 = new Odontologo(92233720368547708L,"50002","Maria","Lopez");

        odontologoServiceEnMemoria.registrarOdontologo(odontologo1);
        odontologoServiceEnMemoria.registrarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoServiceEnMemoria.listarOdontologos();
        assertEquals(2, odontologos.size());
    }

}