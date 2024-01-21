package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.enums.StoreType;
import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "store")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Store extends BaseEntityAudit {
    @Column(nullable = false)
    private String tradeName;
    private String businessName;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany
    private List<Stock> stockList;
    @Column
    private String logoImgUrl;
    @Column
    private double rating;
    @Column
    @Enumerated(value = EnumType.STRING)
    private StoreType storeType;

}
