package com.mogumogu.moru.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TokenInfoDto {

    private String grantType;

    private String accessToken;
    private String urtToken; //refreshToken
    private String uiId;
    private String uiNickName;
    private String uiEmail;

    private int urtKeepLogin; //0이면 로그인 비유지, 1이면 유지

}
