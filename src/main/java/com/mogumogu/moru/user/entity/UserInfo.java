package com.mogumogu.moru.user.entity;

import com.mogumogu.moru.user.dto.UserInfoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER_INFO_TB")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    @Column(name="UI_ID")
    private String uiId;
    private String uiNickname;
    @Builder.Default
    private char uiDel = 'N';
    private int uiPoint;
    private LocalDateTime uiRegist;
    private String uiPassword;
    private String uiEmail;
    private Integer uiHeight;
    private Integer uiAge;
    private String uiGender;

    //DTO -> Entity
    public static UserInfo toEntity(UserInfoDto userInfoDto){
        return UserInfo.builder()
                .uiId(userInfoDto.getUiId())
                .uiNickname(userInfoDto.getUiNickName())
                .uiDel(userInfoDto.getUiDel())
                .uiPoint(userInfoDto.getUiPoint())
                .uiRegist(userInfoDto.getUiRegist())
                .uiPassword(userInfoDto.getUiPassword())
                .uiEmail(userInfoDto.getUiEmail())
                .uiHeight(userInfoDto.getUiHeight())
                .uiAge(userInfoDto.getUiAge())
                .uiGender(userInfoDto.getUiGender())
                .build();
    }
}