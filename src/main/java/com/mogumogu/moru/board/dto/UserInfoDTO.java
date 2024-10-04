package com.mogumogu.moru.board.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserInfoDTO {
    private String uiId;
    private String uiNickname;
    private String uiDel;
    private int uiPoint;
    private LocalDateTime uiRegist;
    private String uiPassword;
    private String uiEmail;
    private Integer uiHeight;
    private Integer uiAge;
    private String uiGender;


}
