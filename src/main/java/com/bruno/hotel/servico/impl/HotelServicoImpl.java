package com.bruno.hotel.servico.impl;

import com.bruno.hotel.entidades.Hotel;
import com.bruno.hotel.repositorios.HotelRepositorio;
import com.bruno.hotel.servico.HotelServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServicoImpl implements HotelServico {

    @Autowired
    private HotelRepositorio repositorio;
    @Override
    public Hotel save(Hotel hotel) {
        return repositorio.save(hotel);
    }

    @Override
    public List<Hotel> findAllHotels() {
        return repositorio.findAll();
    }

    @Override
    public List<Hotel> findAllHotelsByExample(Example example) {
        return repositorio.findAll(example);
    }

    @Override
    public Optional<Hotel> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public void delete(Hotel hotel) {
        repositorio.delete(hotel);
    }
}
