package com.mogumogu.moru.jwt.entity;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
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
public class UserInfoEntity {

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
    private String uiRole;

    //DTO -> Entity
    public static UserInfoEntity toEntity(UserInfoDto userInfoDto){
        return UserInfoEntity.builder()
                .uiId(userInfoDto.getUiId())
                .uiNickname(userInfoDto.getUiNickname())
                .uiDel(userInfoDto.getUiDel())
                .uiPoint(userInfoDto.getUiPoint())
                .uiRegist(userInfoDto.getUiRegist())
                .uiPassword(userInfoDto.getUiPassword())
                .uiEmail(userInfoDto.getUiEmail())
                .uiHeight(userInfoDto.getUiHeight())
                .uiAge(userInfoDto.getUiAge())
                .uiGender(userInfoDto.getUiGender())
                .uiRole(userInfoDto.getUiRole())
                .build();
    }

}