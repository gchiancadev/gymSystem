package dev.gabryel.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice

public class GlobalExcepetionHandler  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarErroValidacao(MethodArgumentNotValidException ex){
        List<String> mensagens =  ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": "+fieldError.getDefaultMessage())
                .toList();

        ErroResponse response =new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                mensagens
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResponse> regraNegocioException(RegraNegocioException ex){
        ErroResponse response = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de regra de negócio",
                List.of(ex.getMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
