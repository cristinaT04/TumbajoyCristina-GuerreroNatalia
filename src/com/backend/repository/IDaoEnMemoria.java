package com.backend.repository;

import java.util.List;

public interface IDaoEnMemoria<T> {
    void registrar(T t);
    List<T> listarTodos();
}
