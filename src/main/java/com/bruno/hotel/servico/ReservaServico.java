package com.bruno.hotel.servico;

import com.bruno.hotel.entidades.Reserva;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface ReservaServico {
    Reserva save(Reserva reserva);
    List<Reserva> findAll();
    List<Reserva> findAll(Example example);

    Optional<Reserva> findById(Integer id);

    void delete(Reserva reserva);
}
