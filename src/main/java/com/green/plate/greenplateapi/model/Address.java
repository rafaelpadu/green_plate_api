package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntity;
import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
