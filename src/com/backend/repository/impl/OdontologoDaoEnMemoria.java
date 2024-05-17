package com.backend.repository.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDaoEnMemoria;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoEnMemoria implements IDaoEnMemoria<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoEnMemoria.class);
    private List<Odontologo> odontologos = new ArrayList<>();
    @Override
    public void registrar(Odontologo odontologo) {
        odontologos.add(odontologo);
        LOGGER.info("Se ha registrado el odontologo: " + odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        List<Odontologo> odontologoList = new ArrayList<>(odontologos);
        LOGGER.info("Lista de odontologos : " + odontologoList);
        return odontologoList;
    }
}
