package com.mogumogu.moru.jwt.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data

public class UserInfoDto {
    @Getter
    @Setter
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
