package com.clinicaodontologica.proyectointegradorfinal.service.impl;

import com.clinicaodontologica.proyectointegradorfinal.entity.Odontologo;
import com.clinicaodontologica.proyectointegradorfinal.repository.OdontologoRepository;
import com.clinicaodontologica.proyectointegradorfinal.service.IOdontologoService;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private OdontologoRepository odontologoRepository;

    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
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
