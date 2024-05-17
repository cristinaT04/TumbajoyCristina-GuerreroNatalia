package com.backend.service.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDaoEnMemoria;
import com.backend.service.IOdontologoServiceEnMemoria;

import java.util.List;

public class OdontologoServiceEnMemoria implements IOdontologoServiceEnMemoria {

    private IDaoEnMemoria<Odontologo> odontologoIDaoEnMemoria;

    public OdontologoServiceEnMemoria(IDaoEnMemoria<Odontologo> odontologoIDaoEnMemoria) {
        this.odontologoIDaoEnMemoria = odontologoIDaoEnMemoria;
    }

    @Override
    public void registrarOdontologo(Odontologo odontologo) {
        odontologoIDaoEnMemoria.registrar(odontologo);
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return odontologoIDaoEnMemoria.listarTodos();
    }
}
