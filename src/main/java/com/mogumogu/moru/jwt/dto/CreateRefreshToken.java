package com.mogumogu.moru.jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRefreshToken {
    private String urtToken;
    private String uiId;
    private String uiNickname;
}
