package com.mogumogu.moru.jwt.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final String uiNickname;

    public CustomAuthenticationToken(Object principal, Object credentials, String uiNickname) {
        super(principal, credentials);
        this.uiNickname = uiNickname;
    }

    public String getNickname() {
        return uiNickname;
    }

}