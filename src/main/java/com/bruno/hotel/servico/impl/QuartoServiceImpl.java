package com.bruno.hotel.servico.impl;

import com.bruno.hotel.entidades.Hotel;
import com.bruno.hotel.entidades.Quarto;
import com.bruno.hotel.repositorios.HotelRepositorio;
import com.bruno.hotel.repositorios.QuartoRepositorio;
import com.bruno.hotel.servico.QuartoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuartoServiceImpl implements QuartoServico {
    @Autowired
    private QuartoRepositorio repositorio;
    @Override
    public Quarto save(Quarto quarto) {
        return repositorio.save(quarto);
    }

    @Override
    public void saveNoReturn(Quarto quarto) {
        repositorio.save(quarto);
    }

    @Override
    public List<Quarto> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<Quarto> findAll(Example example) {
        return repositorio.findAll(example);
    }

    @Override
    public Optional<Quarto> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public void delete(Quarto quarto) {
        repositorio.delete(quarto);
    }
}
