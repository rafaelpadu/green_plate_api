package com.green.plate.greenplateapi.dto;

import com.green.plate.greenplateapi.enums.StoreType;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.green.plate.greenplateapi.model.Store}
 */
@Data
public class StoreDTO implements Serializable {
    Integer id;
    String tradeName;
    String businessName;
    String logoImgUrl;
    Integer addressId;
    double rating;
    StoreType storeType;
}
