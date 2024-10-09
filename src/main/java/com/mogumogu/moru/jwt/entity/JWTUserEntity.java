package com.mogumogu.moru.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class JWTUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uiId;
    private String uiNickname;
    private String uiPassword;
    private String uiEmail;
    private String role;
}
