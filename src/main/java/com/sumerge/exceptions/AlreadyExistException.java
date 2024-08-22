package com.sumerge.exceptions;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}
