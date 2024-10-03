package com.mogumogu.moru.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoDto {
    private String uiId;
    private String uiNickName;
    private String uiDel;
    private int uiPoint;
    private Date uiRegist;
    private String uiPassword;
    private String uiEmail;
    private int uiHeight;
    private int uiAge;
    private String uiGender;
}
