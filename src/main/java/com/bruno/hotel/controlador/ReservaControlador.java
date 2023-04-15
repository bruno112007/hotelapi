package com.bruno.hotel.controlador;

import com.bruno.hotel.dtos.ReservasDTO;
import com.bruno.hotel.entidades.Quarto;
import com.bruno.hotel.entidades.Reserva;
import com.bruno.hotel.excecoes.QuartoNotFoundException;
import com.bruno.hotel.excecoes.ReservaNoutFoundException;
import com.bruno.hotel.servico.QuartoServico;
import com.bruno.hotel.servico.ReservaServico;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(value = "*")
public class ReservaControlador {
    @Autowired
    private ReservaServico servico;
    @Autowired
    private QuartoServico quarto_servico;

    private static final String RESERVA_NAO_ENCONTRADA_MENSAGEM = "A reserva informada não foi encontrada.";

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ReservasDTO dto){
        Reserva reserva = new Reserva();
        BeanUtils.copyProperties(dto, reserva);
        Quarto quarto = quarto_servico.findById(dto.getQuarto()).orElseThrow(() -> new QuartoNotFoundException("O quarto informado não foi encontrado."));
        reserva.setDataInicio(LocalDate.now());
        reserva.setQuarto(quarto);
        reserva.setPrecoTotal(ChronoUnit.DAYS.between(reserva.getDataInicio(), dto.getDataTermino()) * quarto.getPrecoPorDia());
        servico.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reserva registrada com sucesso.");
    }

    @GetMapping("/")
    public List<Reserva> reservas(){
        return servico.findAll();
    }

    @GetMapping("/{id}")
    public Reserva findById(@PathVariable Integer id){
        return servico.findById(id).orElseThrow(() -> new ReservaNoutFoundException(RESERVA_NAO_ENCONTRADA_MENSAGEM));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody@Valid ReservasDTO dto){
        return servico.findById(id).map(reserva -> {
            Reserva reservaUpdate = new Reserva();
            BeanUtils.copyProperties(dto, reservaUpdate);
            reservaUpdate.setId(reserva.getId());
            servico.save(reservaUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("Reserva atualizada com sucesso.");
        }).orElseThrow(() -> new ReservaNoutFoundException(RESERVA_NAO_ENCONTRADA_MENSAGEM));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        servico.delete(servico.findById(id).orElseThrow(() -> new ReservaNoutFoundException(RESERVA_NAO_ENCONTRADA_MENSAGEM)));
        return ResponseEntity.status(HttpStatus.OK).body("Reserva deletada com sucesso.");
    }

    @GetMapping("/filter")
    public List<Reserva> filter(Reserva reserva){
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
        Example example = Example.of(reserva, matcher);
        List<Reserva> resultado = servico.findAll(example);
        return resultado;
    }

}
