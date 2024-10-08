package com.mogumogu.moru.board.entity;

import com.mogumogu.moru.board.dto.UserInfoDTO;
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

    public static UserInfo toEntity(UserInfoDTO userInfoDTO){
        return UserInfo.builder()
                .uiId(userInfoDTO.getUiId())
                .uiNickname(userInfoDTO.getUiNickname())
                .uiDel(userInfoDTO.getUiDel())
                .uiPoint(userInfoDTO.getUiPoint())
                .uiRegist(userInfoDTO.getUiRegist())
                .uiPassword(userInfoDTO.getUiPassword())
                .uiEmail(userInfoDTO.getUiEmail())
                .uiHeight(userInfoDTO.getUiHeight())
                .uiAge(userInfoDTO.getUiAge())
                .uiGender(userInfoDTO.getUiGender())
                .build();
    }
}