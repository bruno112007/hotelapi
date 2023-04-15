package com.bruno.hotel.dtos;

import com.bruno.hotel.entidades.Quarto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ReservasDTO {
    @NotNull(message = "A data de término é obrigatório.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "America/Sao_Paulo")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataTermino;
    @NotNull(message = "A quantidade de hóspedes não pode ser vazio.")
    @Positive(message = "A quantidade de hóspedes não pode ser menor que 0.")
    private Integer quantidadeHospedes;
    @NotNull(message = "A identificação do quarto não pode ser vazia.")
    private Integer quarto;
}
