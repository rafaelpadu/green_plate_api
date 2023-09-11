package com.green.plate.greenplateapi.model;

import com.green.plate.greenplateapi.model.baseEntity.BaseEntityAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "A descrição do produto não pode estar vazia")
    private String description;
    @Column
    private Long EAN13;
    @Column
    private String imageUrl;
    @Column
    private boolean active;
}
