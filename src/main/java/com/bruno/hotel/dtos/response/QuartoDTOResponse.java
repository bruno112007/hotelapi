package com.bruno.hotel.dtos.response;

import com.bruno.hotel.entidades.Hotel;
import com.bruno.hotel.enums.DisponibilidadeQuarto;
import com.bruno.hotel.enums.TipoQuarto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuartoDTOResponse {
    private Integer numero;
    private TipoQuarto tipoQuarto;
    private DisponibilidadeQuarto disponibilidadeQuarto;
    private Hotel hotel;
    private Double precoPorDia;
}
