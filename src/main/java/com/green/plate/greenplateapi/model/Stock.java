package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Stock extends BaseEntityAudit {
    @ManyToOne
    private Store store;
    @ManyToOne
    private Product product;
    @OneToMany
    private List<Price> priceList;
    @Column
    private Integer currentQty;
    @Column
    private Integer minimalQty;
    @Column
    private Integer maxQty;
    @Column
    private LocalDateTime dueDate;
}
