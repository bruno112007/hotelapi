package com.bruno.hotel.servico;

import com.bruno.hotel.entidades.Pessoa;
import com.bruno.hotel.entidades.Quarto;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface QuartoServico {
    Quarto save(Quarto quarto);
    void saveNoReturn(Quarto quarto);

    List<Quarto> findAll();
    List<Quarto> findAll(Example example);

    Optional<Quarto> findById(Integer id);

    void delete(Quarto quarto);
}
