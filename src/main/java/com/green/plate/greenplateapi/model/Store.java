package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "store")
public class Store extends BaseEntityAudit {
    @Column(nullable = false)
    private String tradeName;
    private String businessName;
    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @OneToMany
    private List<Stock> stockList;
    @Column
    private String logoImgUrl;

}
