package com.bruno.hotel.dtos;

import com.bruno.hotel.anotacoes.Telefone;
import com.bruno.hotel.entidades.Quarto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelDTO {
    @NotBlank(message = "O campo nome não deve estar em branco.")
    private String nome;
    @NotBlank(message = "O campo endereco não deve estar em branco.")
    private String endereco;
    @NotBlank(message = "O campo telefone não deve estar em branco.")
    @Telefone(message = "O telefone informado é inválido.")
    private String telefone;

    private List<Quarto> quartos;
}
