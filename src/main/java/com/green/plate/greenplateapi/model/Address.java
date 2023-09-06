package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
public class Address extends BaseEntityAudit {
    @Column
    private Integer cep;
    @Column
    private String country;
    @Column
    private String state;
    @Column
    private String city;
    @Column
    private String address;
    @Column
    private Integer number;
    @Column
    private String complement;
}
