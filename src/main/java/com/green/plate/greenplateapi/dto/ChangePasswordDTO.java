package com.green.plate.greenplateapi.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    private Integer userId;
    private String oldPassword;
    private String newPassword;
}
