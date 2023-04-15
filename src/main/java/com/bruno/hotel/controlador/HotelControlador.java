package com.bruno.hotel.controlador;

import com.bruno.hotel.dtos.HotelDTO;
import com.bruno.hotel.entidades.Hotel;
import com.bruno.hotel.excecoes.HotelNotFoundException;
import com.bruno.hotel.repositorios.HotelRepositorio;
import com.bruno.hotel.servico.HotelServico;
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
@RequestMapping("/api/hotel")
@CrossOrigin(value = "*")
public class HotelControlador {
    @Autowired
    private HotelServico servico;

    private static final String HOTEL_NAO_ENCONTRADO_MENSAGEM = "Hotel não encontrado de acordo com o filtro.";
    @Autowired
    private HotelRepositorio hotelRepositorio;

    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody@Valid HotelDTO dto){
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(dto, hotel);
        servico.save(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hotel criado com sucesso.");
    }

    @GetMapping("/")
    public List<Hotel> hotels(){
        return servico.findAllHotels();
    }

    @GetMapping("/{id}")
    public Hotel findById(@PathVariable Integer id){
        return servico.findById(id).orElseThrow(() -> new HotelNotFoundException(HOTEL_NAO_ENCONTRADO_MENSAGEM));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody@Valid HotelDTO dto){
        return servico.findById(id).map(hotel -> {
            Hotel hotelUpdate = new Hotel();
            BeanUtils.copyProperties(dto, hotelUpdate);
            hotelUpdate.setId(hotel.getId());
            servico.save(hotelUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("Hotel atualizado com sucesso.");
        }).orElseThrow(() -> new HotelNotFoundException(HOTEL_NAO_ENCONTRADO_MENSAGEM));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        servico.delete(servico.findById(id).orElseThrow(() -> new HotelNotFoundException(HOTEL_NAO_ENCONTRADO_MENSAGEM)));
        return ResponseEntity.status(HttpStatus.OK).body("Hotel deletado com sucesso.");
    }

    @GetMapping("/filter")
    public List<Hotel> filter(Hotel hotel){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(hotel, matcher);
        List<Hotel> resultado = servico.findAllHotelsByExample(example);
        return resultado;
    }
}
