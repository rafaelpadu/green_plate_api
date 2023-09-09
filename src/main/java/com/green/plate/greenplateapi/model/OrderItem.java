package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import com.green.plate.greenplateapi.utils.priceValidator.ValidPrice;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntityAudit {
    @ManyToOne
    @JoinColumn
    private Stock stock;
    @ValidPrice
    @Column
    private BigDecimal itemTotal;
    @ValidPrice
    @Column
    private BigDecimal unitValue;
    @ValidPrice
    @Column
    private BigDecimal discount;
    @Column
    private Integer qtyRequested;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
