package com.bruno.hotel.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaDTO {
    @NotBlank(message = "O campo nome não pode estar em branco.")
    private String nome;
    @NotBlank(message = "O campo email não pode estar em branco.")
    @Email(message = "O email informado é inválido.")
    private String email;
    @NotBlank(message = "O campo cpf não pode estar em branco.")
    @CPF(message = "O cpf informado é inválido")
    private String cpf;
    @NotNull(message = "A data de nascimento não pode estar vazia.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "America/Sao_Paulo")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;
}
