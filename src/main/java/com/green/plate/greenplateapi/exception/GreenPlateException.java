package com.green.plate.greenplateapi.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class GreenPlateException extends RuntimeException{
    private String errorMessage;
    private Integer statusCode;
    private ZonedDateTime zonedDateTime;
}
