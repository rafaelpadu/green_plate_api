package com.green.plate.greenplateapi.exception;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class GreenPlateException extends RuntimeException{
    private String errorMessage;
    private Integer statusCode;
    private ZonedDateTime zonedDateTime;
}
