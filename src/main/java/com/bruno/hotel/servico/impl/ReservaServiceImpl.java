package com.bruno.hotel.servico.impl;

import com.bruno.hotel.entidades.Reserva;
import com.bruno.hotel.repositorios.ReservaRepositorio;
import com.bruno.hotel.servico.ReservaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaServico {
    @Autowired
    private ReservaRepositorio repositorio;

    @Override
    public Reserva save(Reserva reserva) {
        return repositorio.save(reserva);
    }

    @Override
    public List<Reserva> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<Reserva> findAll(Example example) {
        return repositorio.findAll(example);
    }

    @Override
    public Optional<Reserva> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public void delete(Reserva reserva) {
        repositorio.delete(reserva);
    }

}
