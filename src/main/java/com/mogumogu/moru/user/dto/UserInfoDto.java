package com.mogumogu.moru.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserInfoDto {
    private String uiId;
    private String uiNickname;
    private char uiDel;
    private int uiPoint;
    private LocalDateTime uiRegist;
    private String uiPassword;
    private String uiEmail;
    private int uiHeight;
    private int uiAge;
    private String uiGender;
    private String uiRole;

}
