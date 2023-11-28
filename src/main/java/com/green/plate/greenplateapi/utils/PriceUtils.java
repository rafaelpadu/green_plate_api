package com.green.plate.greenplateapi.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Component
public class PriceUtils {
    public static BigDecimal roundOrFixPrice(BigDecimal price) {
        if (price == null) {
            return null;
        }

        int scale = 2;
        RoundingMode roundingMode = RoundingMode.HALF_UP;
        BigDecimal roundedValue = price.setScale(scale, roundingMode);

        if (!roundedValue.equals(price)) {
            return roundedValue;
        }

        return price;
    }
}
