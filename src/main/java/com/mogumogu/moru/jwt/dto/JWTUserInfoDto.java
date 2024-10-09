package com.mogumogu.moru.jwt.dto;

import lombok.Data;

@Data

public class JWTUserInfoDto {

    private String uiId;
    private String uiNickname;
    private String uiPassword;
    private String uiEmail;
    private String role;
}
