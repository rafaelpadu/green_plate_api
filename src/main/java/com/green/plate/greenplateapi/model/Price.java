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
public class Price extends BaseEntityAudit {
    @ValidPrice
    @Column(name = "unit_value")
    private BigDecimal unitValue;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
}
