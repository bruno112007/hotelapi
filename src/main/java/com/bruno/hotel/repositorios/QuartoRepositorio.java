package com.bruno.hotel.repositorios;

import com.bruno.hotel.entidades.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuartoRepositorio extends JpaRepository<Quarto, Integer> {
}
