package com.bancopan.address.exception;

import com.bancopan.address.exception.dto.ApiErrorDTO;
import com.bancopan.address.rest.client.exception.ErroConsultaClientException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ErroConsultaClientException.class)
    public ResponseEntity<ApiErrorDTO> handleException(Exception ex, WebRequest request){

        ApiErrorDTO erroDTO = new ApiErrorDTO();
        erroDTO.setMensagem("Erro ao realizar consulta, favor verificar com suporte.");

        return new ResponseEntity<>(erroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<ApiErrorDTO> handleConstraintValidationException(javax.validation.ConstraintViolationException ex, WebRequest request){

        ApiErrorDTO erroDTO = new ApiErrorDTO();
        erroDTO.setMensagem(ex.getMessage());

        return new ResponseEntity<>(erroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorDTO> handleConstraintException(ConstraintViolationException ex, WebRequest request){

        ApiErrorDTO erroDTO = new ApiErrorDTO();
        erroDTO.setMensagem("Erro ao inserir cliente.");

        return new ResponseEntity<>(erroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
