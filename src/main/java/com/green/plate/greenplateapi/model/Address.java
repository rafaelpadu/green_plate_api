package com.green.plate.greenplateapi.model;

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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String createdBy;
    private String updatedBy;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
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
