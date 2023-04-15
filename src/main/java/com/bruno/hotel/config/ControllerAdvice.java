package com.bruno.hotel.config;

import com.bruno.hotel.excecoes.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleValidationExceptions(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErros(errors);
    }

    @ExceptionHandler(HotelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleHotelNotFound(HotelNotFoundException e) {
        return new ApiErros(e.getMessage());
    }

    @ExceptionHandler(IdadeNaoPermitidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleIdadeNaoPermitida(IdadeNaoPermitidaException e) {
        return new ApiErros(e.getMessage());
    }

    @ExceptionHandler(PessoaNoutFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePessoaNotFound(PessoaNoutFoundException e) {
        return new ApiErros(e.getMessage());
    }

    @ExceptionHandler(QuartoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleQuartoNotFound(QuartoNotFoundException e) {
        return new ApiErros(e.getMessage());
    }

    @ExceptionHandler(ReservaNoutFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleReservaNotFound(ReservaNoutFoundException e) {
        return new ApiErros(e.getMessage());
    }
}
