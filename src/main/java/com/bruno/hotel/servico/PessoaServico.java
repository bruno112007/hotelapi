package com.bruno.hotel.servico;

import com.bruno.hotel.entidades.Pessoa;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface PessoaServico {
    Pessoa save(Pessoa pessoa);
    void saveNoReturn(Pessoa pessoa);

    List<Pessoa> findAll();
    List<Pessoa> findAll(Example example);

    Optional<Pessoa> findById(Integer id);

    void delete(Pessoa pessoa);
}
