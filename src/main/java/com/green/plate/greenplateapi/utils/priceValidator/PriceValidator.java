package com.green.plate.greenplateapi.utils.priceValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceValidator implements ConstraintValidator<ValidPrice, BigDecimal> {

    @Override
    public void initialize(ValidPrice constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if(value == null){
            return false;
        }
        int scale = 2;
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        BigDecimal roundedValue = value.setScale(scale, roundingMode);
        return roundedValue.equals(value);
    }
}
