package com.clinicaodontologica.proyectointegradorfinal.service.impl;

import com.clinicaodontologica.proyectointegradorfinal.entity.Odontologo;
import com.clinicaodontologica.proyectointegradorfinal.repository.OdontologoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private OdontologoService odontologoService;
    @Test
    public void deberiaRegistrarseUnOdontologo(){

        odontologoService = new OdontologoService(new OdontologoRepository() {
            @Override
            public List<Odontologo> findAll() {
                return null;
            }

            @Override
            public List<Odontologo> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Odontologo> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends Odontologo> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Odontologo> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Odontologo> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Odontologo> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Odontologo getOne(Long aLong) {
                return null;
            }

            @Override
            public Odontologo getById(Long aLong) {
                return null;
            }

            @Override
            public Odontologo getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Odontologo> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Odontologo> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Odontologo> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Odontologo> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Odontologo> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Odontologo entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Odontologo> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Odontologo> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Odontologo> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Odontologo> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Odontologo> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Odontologo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        });
        Odontologo odontologo = new Odontologo("50003", "Lucia","Lopez");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoRegistrado.getMatricula());
        assertNotNull(odontologoRegistrado.getId());

    }
    @Test
    public void deberiaListarTodosLosOdontologosRegistrados() {
        odontologoService = new OdontologoService(new OdontologoRepository() {
            @Override
            public List<Odontologo> findAll() {
                return null;
            }

            @Override
            public List<Odontologo> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Odontologo> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends Odontologo> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Odontologo> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Odontologo> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Odontologo> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Odontologo getOne(Long aLong) {
                return null;
            }

            @Override
            public Odontologo getById(Long aLong) {
                return null;
            }

            @Override
            public Odontologo getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Odontologo> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Odontologo> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Odontologo> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Odontologo> S save(S entity) {
                return null;
            }

            @Override
            public Optional<Odontologo> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Odontologo entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Odontologo> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Odontologo> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Odontologo> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Odontologo> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Odontologo> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Odontologo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        });
        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }

}
