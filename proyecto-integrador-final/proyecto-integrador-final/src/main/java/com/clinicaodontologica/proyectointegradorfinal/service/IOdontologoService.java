package com.clinicaodontologica.proyectointegradorfinal.service;

import com.clinicaodontologica.proyectointegradorfinal.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    Odontologo registrarOdontologo(Odontologo odontologo);
    List<Odontologo> listarOdontologos();
}

