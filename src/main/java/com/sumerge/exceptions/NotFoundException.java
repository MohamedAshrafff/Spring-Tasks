package com.sumerge.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String errorMessage){
        super(errorMessage);
    }
}
