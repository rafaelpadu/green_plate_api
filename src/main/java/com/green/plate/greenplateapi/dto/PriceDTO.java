package com.green.plate.greenplateapi.dto;

import com.green.plate.greenplateapi.model.Price;
import com.green.plate.greenplateapi.utils.priceValidator.ValidPrice;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO for {@link Price}
 */
@Data
public class PriceDTO implements Serializable {
    Integer id;
    Date createdAt;
    Date updatedAt;
    @NotNull(message = "O valor do preço não pode ser nulo")
    @ValidPrice
    BigDecimal unitValue;
    @NotNull(message = "O preço precisa pertencer a um estoque")
    Integer stockId;
}
