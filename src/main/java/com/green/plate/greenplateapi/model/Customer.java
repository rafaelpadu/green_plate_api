package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
