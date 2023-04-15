package com.bruno.hotel.dtos;

import com.bruno.hotel.anotacoes.ListNotEmpty;
import com.bruno.hotel.entidades.Hotel;
import com.bruno.hotel.entidades.Reserva;
import com.bruno.hotel.enums.DisponibilidadeQuarto;
import com.bruno.hotel.enums.TipoQuarto;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;

import java.util.List;

@Getter
@Setter
public class QuartoDTO {
    @NotNull(message = "O campo número não pode ser vazio.")
    private Integer numero;
    @NotBlank(message = "O campo 'tipo do quarto' não pode estar em branco.")
    private String tipoQuarto;
    @NotBlank(message = "O campo 'disponibilidade do quarto' não pode estar em branco.")
    private String disponibilidadeQuarto;
    @NotNull(message = "Hotel não pode estar nulo.")
    private Integer hotel;
    @NotNull(message = "O quarto deve ter um preço/dia")
    private Double precoPorDia;
}
