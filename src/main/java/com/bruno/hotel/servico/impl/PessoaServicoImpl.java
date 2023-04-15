package com.bruno.hotel.servico.impl;

import com.bruno.hotel.entidades.Pessoa;
import com.bruno.hotel.excecoes.IdadeNaoPermitidaException;
import com.bruno.hotel.repositorios.PessoaRepositorio;
import com.bruno.hotel.servico.PessoaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaServicoImpl implements PessoaServico {

    @Autowired
    private PessoaRepositorio repositorio;

    @Override
    public Pessoa save(Pessoa pessoa) {
        Period periodo = Period.between(pessoa.getDataNascimento(), LocalDate.now());
        int idade = periodo.getYears();
        if(idade < 16){
            throw new IdadeNaoPermitidaException("Você tem apenas " + idade + " anos, e por isso, não pode se registrar.");
        }
        return repositorio.save(pessoa);
    }

    @Override
    public void saveNoReturn(Pessoa pessoa) {
        repositorio.save(pessoa);
    }

    @Override
    public List<Pessoa> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<Pessoa> findAll(Example example) {
        return repositorio.findAll(example);
    }

    @Override
    public Optional<Pessoa> findById(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public void delete(Pessoa pessoa) {
        repositorio.delete(pessoa);
    }
}
