package com.bruno.hotel.servico;

import com.bruno.hotel.entidades.Hotel;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface HotelServico {
    Hotel save(Hotel hotel);

    List<Hotel> findAllHotels();
    List<Hotel> findAllHotelsByExample(Example example);

    Optional<Hotel> findById(Integer id);

    void delete(Hotel hotel);
}
