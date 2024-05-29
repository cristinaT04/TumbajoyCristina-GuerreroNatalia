package com.clinicaodontologica.proyectointegradorfinal.service.impl;

import com.clinicaodontologica.proyectointegradorfinal.entity.Odontologo;
import com.clinicaodontologica.proyectointegradorfinal.repository.IDao;
import com.clinicaodontologica.proyectointegradorfinal.service.IOdontologoService;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }


    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return null;
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return List.of();
    }
}
