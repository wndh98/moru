package com.mogumogu.moru.jwt.dto;

import lombok.Data;


import java.time.LocalDateTime;

@Data

public class UserInfoDto {
    private String uiId;
    private String uiNickname;
    private String uiPassword;
    private String uiEmail;
    private String uiRole;

}
