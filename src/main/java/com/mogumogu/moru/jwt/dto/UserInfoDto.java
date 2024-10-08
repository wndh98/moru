package com.mogumogu.moru.jwt.dto;

import lombok.Data;
import java.util.Date;

@Data

public class UserInfoDto {

    private String uiId;
    private String uiNickname;
    private String uiPassword;
    private String uiEmail;
    private String role;
}
