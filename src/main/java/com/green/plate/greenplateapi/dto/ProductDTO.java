package com.green.plate.greenplateapi.dto;

import com.green.plate.greenplateapi.enums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.green.plate.greenplateapi.model.Product}
 */
@Data
public class ProductDTO implements Serializable {
    Integer id;
    Date createdAt;
    Date updatedAt;
    String name;
    @NotBlank(message = "A descrição do produto não pode estar vazia")
    String description;
    Long EAN13;
    String imageUrl;
    boolean active;
    @NotNull(message = "Todo produto precisa ter uma categoria")
    String productCategory;

}
