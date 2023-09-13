package com.green.plate.greenplateapi.dto;

import com.green.plate.greenplateapi.model.OrderItem;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO for {@link OrderItem}
 */
@Data
public class OrderItemDTO implements Serializable {
    Integer id;
    String createdBy;
    String updatedBy;
    Date createdAt;
    Date updatedAt;
    @NotNull
    Integer stockId;
    BigDecimal itemTotal;
    BigDecimal unitValue;
    BigDecimal discount;
    Integer qtyRequested;
    Integer pedidoId;
}
