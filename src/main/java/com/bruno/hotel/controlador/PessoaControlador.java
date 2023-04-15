package com.bruno.hotel.controlador;

import com.bruno.hotel.dtos.PessoaDTO;
import com.bruno.hotel.entidades.Hotel;
import com.bruno.hotel.entidades.Pessoa;
import com.bruno.hotel.excecoes.HotelNotFoundException;
import com.bruno.hotel.excecoes.PessoaNoutFoundException;
import com.bruno.hotel.servico.PessoaServico;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(value = "*")
public class PessoaControlador {
    @Autowired
    private PessoaServico servico;

    private static final String PESSOA_NAO_ENCONTRADA_MENSAGEM = "A pessoa informada não foi encontrada.";

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody@Valid PessoaDTO dto){
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(dto, pessoa);
        servico.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa registrada com sucesso.");
    }

    @GetMapping("/")
    public List<Pessoa> pessoas(){
        return servico.findAll();
    }

    @GetMapping("/{id}")
    public Pessoa findById(@PathVariable Integer id){
        return servico.findById(id).orElseThrow(() -> new PessoaNoutFoundException(PESSOA_NAO_ENCONTRADA_MENSAGEM));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody@Valid PessoaDTO dto){
        return servico.findById(id).map(pessoa -> {
            Pessoa pessoaUpdate = new Pessoa();
            BeanUtils.copyProperties(dto, pessoaUpdate);
            pessoaUpdate.setId(pessoa.getId());
            servico.save(pessoaUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("Pessoa atualizado com sucesso.");
        }).orElseThrow(() -> new PessoaNoutFoundException(PESSOA_NAO_ENCONTRADA_MENSAGEM));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        servico.delete(servico.findById(id).orElseThrow(() -> new PessoaNoutFoundException(PESSOA_NAO_ENCONTRADA_MENSAGEM)));
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso.");
    }

    @GetMapping("/filter")
    public List<Pessoa> filter(Pessoa pessoa){
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
        Example example = Example.of(pessoa, matcher);
        List<Pessoa> resultado = servico.findAll(example);
        return resultado;
    }
}
