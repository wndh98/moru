package com.mogumogu.moru.jwt.dto;

import lombok.Data;

@Data

public class JWTUserInfoDto {

    private String uiId;
    private String uiPassword;
    private String uiNickname;
//    private String uiEmail;

    private String uiRole;
}
