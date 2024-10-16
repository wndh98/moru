package com.mogumogu.moru.jwt.entity;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.user.entity.UserWeightEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_INFO_TB")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoEntity {

    @Id
    @Column(unique = true)
    private String uiId;
    private String uiNickname;
    @Builder.Default
    private char uiDel = 'N';
    private int uiPoint;
    @Builder.Default
    private LocalDateTime uiRegist = LocalDateTime.now();
    private String uiPassword;
    private String uiEmail;
    private Integer uiHeight;
    private Integer uiAge;
    private String uiGender;
    private String uiRole;
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.REMOVE)
    private List<UserWeightEntity> userWeightList = new ArrayList<>();
//    @OneToMany(mappedBy = "diaryWalk", cascade = CascadeType.REMOVE)
//    private List<Diarywalk> diaryWalkList = new ArrayList<>();


    //DTO -> Entity
    public static UserInfoEntity toEntity(UserInfoDto userInfoDto) {
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
    public void prePersist() {
        if (uiRegist == null) {
            this.uiRegist = LocalDateTime.now();
        }
    }
}