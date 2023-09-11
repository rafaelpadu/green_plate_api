package com.green.plate.greenplateapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.green.plate.greenplateapi.model.Product}
 */
@Data
public class ProductDTO implements Serializable {
    Integer id;
    String createdBy;
    String updatedBy;
    Date createdAt;
    Date updatedAt;
    String name;
    @NotBlank(message = "A descrição do produto não pode estar vazia")
    String description;
    Long EAN13;
    String imageUrl;
    boolean active;
}
