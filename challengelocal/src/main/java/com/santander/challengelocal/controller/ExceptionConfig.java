package com.santander.challengelocal.controller;

import com.santander.challengelocal.dto.StatusDto;
import com.santander.challengelocal.exceptions.UserAlreadyCreatedException;
import com.santander.challengelocal.exceptions.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(UserAlreadyCreatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StatusDto handlerException(UserAlreadyCreatedException userAlreadyCreatedException) {
        return new StatusDto(400,userAlreadyCreatedException.getMessage());
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StatusDto handlerException(UserNotExistException userNotExistException) {
        return new StatusDto(400, userNotExistException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected StatusDto handleValidationExceptions(MethodArgumentNotValidException e) {
        return new StatusDto(400,
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected StatusDto handleValidationExceptions(HttpMessageNotReadableException e) {
        return new StatusDto(400, e.getMessage());
    }


}
