package com.green.plate.greenplateapi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.green.plate.greenplateapi.model.Address}
 */
@Data
public class AddressDTO implements Serializable {
    Integer id;
    Date createdAt;
    Date updatedAt;
    Integer cep;
    String country;
    String state;
    String city;
    String address;
    Integer number;
    String complement;
}
