package com.bruno.hotel.repositorios;

import com.bruno.hotel.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepositorio extends JpaRepository<Hotel, Integer> {
}
