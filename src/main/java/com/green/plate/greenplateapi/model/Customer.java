package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Customer extends BaseEntityAudit {
    @Column
    private String fullName;
    @Column
    private String phone;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne
    private Usuario usuario;
}
