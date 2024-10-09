package com.mogumogu.moru.board.dto;

import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String uiId;
    private String uiNickname;
    private char uiDel;
    private int uiPoint;
    private LocalDateTime uiRegist;
    private String uiPassword;
    private String uiEmail;
    private Integer uiHeight;
    private Integer uiAge;
    private String uiGender;
    public static UserInfoDTO toDTO(UserInfo userInfo){
        return UserInfoDTO.builder()
                .uiId(userInfo.getUiId())
                .uiNickname(userInfo.getUiNickname())
                .uiDel(userInfo.getUiDel())
                .uiPoint(userInfo.getUiPoint())
                .uiRegist(userInfo.getUiRegist())
                .uiPassword(userInfo.getUiPassword())
                .uiEmail(userInfo.getUiEmail())
                .uiHeight(userInfo.getUiHeight())
                .uiAge(userInfo.getUiAge())
                .uiGender(userInfo.getUiGender())
                .build();
    }

}
