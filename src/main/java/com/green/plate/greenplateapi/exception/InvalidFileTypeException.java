package com.green.plate.greenplateapi.exception;

import lombok.Data;

@Data
public class InvalidFileTypeException extends RuntimeException{
    private final String message;

    public InvalidFileTypeException(String message) {
        super(message);
        this.message = message;
    }
}
