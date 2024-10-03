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

}
