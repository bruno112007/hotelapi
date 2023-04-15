package com.bruno.hotel.excecoes;

public class QuartoNotFoundException extends RuntimeException{
    public QuartoNotFoundException(String message) {
        super(message);
    }
}
