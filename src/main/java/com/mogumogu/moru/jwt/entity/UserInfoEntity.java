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
    @Builder.Default
    private LocalDateTime uiRegist=LocalDateTime.now();
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
                .uiPassword(userInfoDto.getUiPassword())
                .uiNickname(userInfoDto.getUiNickname())
                .uiPoint(userInfoDto.getUiPoint())
                .uiRegist(userInfoDto.getUiRegist())
                .uiEmail(userInfoDto.getUiEmail())
                .uiHeight(userInfoDto.getUiHeight())
                .uiAge(userInfoDto.getUiAge())
                .uiGender(userInfoDto.getUiGender())
                .uiRole(userInfoDto.getUiRole())
                .build();
    }

    @PrePersist
    public void prePersist(){
        if(uiRegist == null){
            this.uiRegist = LocalDateTime.now();
        }
    }

}