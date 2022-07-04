package com.santander.challengelocal.exceptions;

public class UserAlreadyCreatedException extends RuntimeException{
    public UserAlreadyCreatedException(String message) {
        super(message);
    }
}
