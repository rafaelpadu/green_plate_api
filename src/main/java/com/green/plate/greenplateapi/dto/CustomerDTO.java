package com.green.plate.greenplateapi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.green.plate.greenplateapi.model.Customer}
 */
@Data
public class CustomerDTO implements Serializable {
    Integer id;
    String fullName;
    String phone;
    Integer usuarioId;
}
