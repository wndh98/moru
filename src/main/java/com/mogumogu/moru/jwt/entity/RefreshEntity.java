package com.mogumogu.moru.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="USER_REFRESH_TOKEN_TB")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshEntity {
    @Id
    @Column(name = "URT_TOKEN")
    private String urtToken; //refreshToken
    private String uiId; //id
    private String uiNickname;
    private String expiration;

    public static RefreshEntity toEntity(RefreshEntity refreshEntity) {
        return refreshEntity.builder()
                .urtToken(refreshEntity.getUrtToken())
                .uiId(refreshEntity.getUiId())
                .uiNickname(refreshEntity.getUiNickname())
                .expiration(refreshEntity.getExpiration())
                .build();
    }
}
