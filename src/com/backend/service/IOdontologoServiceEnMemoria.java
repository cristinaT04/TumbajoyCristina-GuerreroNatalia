package com.backend.service;

import com.backend.entity.Odontologo;

import java.util.List;

public interface IOdontologoServiceEnMemoria {
    void registrarOdontologo(Odontologo odontologo);
    List<Odontologo> listarOdontologos();
}
