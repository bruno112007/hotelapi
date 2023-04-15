package com.bruno.hotel.controlador;

import com.bruno.hotel.dtos.QuartoDTO;
import com.bruno.hotel.dtos.response.QuartoDTOResponse;
import com.bruno.hotel.entidades.Hotel;
import com.bruno.hotel.entidades.Quarto;
import com.bruno.hotel.enums.DisponibilidadeQuarto;
import com.bruno.hotel.enums.TipoQuarto;
import com.bruno.hotel.excecoes.HotelNotFoundException;
import com.bruno.hotel.excecoes.QuartoNotFoundException;
import com.bruno.hotel.servico.HotelServico;
import com.bruno.hotel.servico.QuartoServico;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/quartos")
@CrossOrigin(value = "*")
public class QuartoControlador {
    @Autowired
    private QuartoServico servico;
    @Autowired
    private HotelServico hotel_servico;

    private static final String QUARTO_NAO_ENCONTRADO_MENSAGEM = "O quarto informado não foi encontrado.";

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody@Valid QuartoDTO dto){
        Quarto quarto = new Quarto();
        BeanUtils.copyProperties(dto, quarto);
        Hotel hotel = hotel_servico.findById(dto.getHotel()).orElseThrow(() -> new HotelNotFoundException("Hotel não encontrado no campo 'hotel'"));
        quarto.setHotel(hotel);
        quarto.setDisponibilidadeQuarto(DisponibilidadeQuarto.valueOf(dto.getDisponibilidadeQuarto()));
        quarto.setTipoQuarto(TipoQuarto.valueOf(dto.getTipoQuarto()));
        servico.save(quarto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Quarto registrado com sucesso.");
    }

    @GetMapping("/")
    public List<QuartoDTOResponse> quartos(){
        List<Quarto> quartosEntidades = servico.findAll();
        List<QuartoDTOResponse> respostas = new ArrayList<>();
        for (Quarto quartoEntidade : quartosEntidades) {
            QuartoDTOResponse resposta = QuartoDTOResponse.builder()
                    .numero(quartoEntidade.getNumero())
                    .disponibilidadeQuarto(quartoEntidade.getDisponibilidadeQuarto())
                    .tipoQuarto(quartoEntidade.getTipoQuarto())
                    .precoPorDia(quartoEntidade.getPrecoPorDia())
                    .hotel(quartoEntidade.getHotel())
                    .build();
            respostas.add(resposta);
        }
        return respostas;
    }

    @GetMapping("/{id}")
    public Quarto findById(@PathVariable Integer id){
        return servico.findById(id).orElseThrow(() -> new QuartoNotFoundException(QUARTO_NAO_ENCONTRADO_MENSAGEM));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody@Valid QuartoDTO dto){
        return servico.findById(id).map(quarto -> {
            Quarto quartoUpdate = new Quarto();
            BeanUtils.copyProperties(dto, quartoUpdate);
            quartoUpdate.setId(quarto.getId());
            servico.save(quartoUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("Quarto atualizado com sucesso.");
        }).orElseThrow(() -> new QuartoNotFoundException(QUARTO_NAO_ENCONTRADO_MENSAGEM));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        servico.delete(servico.findById(id).orElseThrow(() -> new QuartoNotFoundException(QUARTO_NAO_ENCONTRADO_MENSAGEM)));
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso.");
    }

    @GetMapping("/filter")
    public List<Quarto> filter(Quarto quarto){
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
        Example example = Example.of(quarto, matcher);
        List<Quarto> resultado = servico.findAll(example);
        return resultado;
    }
}
