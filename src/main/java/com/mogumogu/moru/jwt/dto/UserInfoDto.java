package com.mogumogu.moru.jwt.dto;

import lombok.Data;


import java.time.LocalDateTime;

@Data

public class UserInfoDto {
    private String uiId;
    private String uiPassword;
    private String uiRole;

}
