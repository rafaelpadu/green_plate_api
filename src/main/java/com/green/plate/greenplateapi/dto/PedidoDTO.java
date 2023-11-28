package com.green.plate.greenplateapi.dto;

import com.green.plate.greenplateapi.model.Pedido;
import com.green.plate.greenplateapi.utils.PriceUtils;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link Pedido}
 */
@Data
public class PedidoDTO implements Serializable {
    Integer id;
    Date createdAt;
    Date updatedAt;
    @NotNull
    Integer storeId;
    @NotNull
    Integer customerId;
    List<OrderItemDTO> orderItemList;
    BigDecimal itemTotal;


    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = PriceUtils.roundOrFixPrice(itemTotal);
    }
}
