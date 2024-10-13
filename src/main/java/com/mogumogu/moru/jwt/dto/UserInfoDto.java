package com.mogumogu.moru.jwt.dto;

import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private String uiId;
    private String uiNickname;
    private String uiDel;
    private int uiPoint;
    private LocalDateTime uiRegist;
    private String uiPassword;
    private String uiEmail;
    private int uiHeight;
    private int uiAge;
    private String uiGender;
    private String uiRole;

    public static UserInfoDto toDto(UserInfoEntity userInfoEntity){
        return UserInfoDto.builder()
                .uiId(userInfoEntity.getUiId())
                .uiPassword(userInfoEntity.getUiPassword())
                .uiNickname(userInfoEntity.getUiNickname())
                .uiPoint(userInfoEntity.getUiPoint())
                .uiRegist(userInfoEntity.getUiRegist())
                .uiEmail(userInfoEntity.getUiEmail())
                .uiHeight(userInfoEntity.getUiHeight())
                .uiAge(userInfoEntity.getUiAge())
                .uiGender(userInfoEntity.getUiGender())
                .uiRole(userInfoEntity.getUiRole())
                .build();
    }

}
