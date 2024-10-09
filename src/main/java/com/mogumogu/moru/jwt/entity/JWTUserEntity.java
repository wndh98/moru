package com.mogumogu.moru.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import jakarta.persistence.*;


@Entity
@Table(name = "USER_INFO_TB")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JWTUserEntity {

    @Id
    @Column(name = "UI_ID")
    private String uiId;
    private String uiNickname;
    private String uiPassword;
    private String uiEmail;
    private String uiRole;

    public static JWTUserEntity toEntity(JWTUserEntity jwtUserEntity) {
        return jwtUserEntity.builder()
                .uiId(jwtUserEntity.getUiId())
                .uiNickname(jwtUserEntity.getUiNickname())
                .uiPassword(jwtUserEntity.getUiPassword())
                .uiEmail(jwtUserEntity.getUiEmail())
                .uiRole(jwtUserEntity.getUiRole())
                .build();
    }
}

