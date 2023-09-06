package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import com.green.plate.greenplateapi.utils.priceValidator.ValidPrice;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Order extends BaseEntityAudit {

    @OneToMany
    @JoinColumn
    private List<OrderItem> orderItemList;
    @ManyToOne
    @JoinColumn
    private Store store;
    @ManyToOne
    @JoinColumn
    private Customer customer;
    @ValidPrice
    @Column
    private BigDecimal itemTotal;
}
