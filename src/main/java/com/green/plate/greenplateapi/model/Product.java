package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntityAudit {
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Long EAN13;
    @Column
    private String imageUrl;
    @Column
    private boolean active;
}
