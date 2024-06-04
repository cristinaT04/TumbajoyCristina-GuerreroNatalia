package com.clinicaodontologica.proyectointegradorfinal.repository;

import com.clinicaodontologica.proyectointegradorfinal.entity.Paciente;

import java.util.List;

public interface IDao<T> {
    T registrar(T t);

    T buscarPorId(Long id);

    List<T> listarTodos();
}
