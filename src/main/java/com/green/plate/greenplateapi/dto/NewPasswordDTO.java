package com.green.plate.greenplateapi.dto;

import lombok.Data;

@Data
public class NewPasswordDTO {

    private String userName;
    private String oldPassword;
    private String newPassword;
}
