package com.mogumogu.moru.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoDto {
    private String uiId;
    private String uiNickName;
    private char uiDel;
    private int uiPoint;
    private LocalDateTime uiRegist;
    private String uiPassword;
    private String uiEmail;
    private int uiHeight;
    private int uiAge;
    private String uiGender;
}
