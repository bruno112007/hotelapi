package com.bruno.hotel.config;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErros {
    private List<String> erros;

    public ApiErros(List<String> erros){
        this.erros = erros;
    }

    public ApiErros(String erro){
        this.erros = Arrays.asList(erro);
    }
}
