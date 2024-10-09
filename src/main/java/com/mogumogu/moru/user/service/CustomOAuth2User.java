package com.mogumogu.moru.user.service;

import com.mogumogu.moru.user.dto.UserInfoDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final UserInfoDto userInfoDto;

    public CustomOAuth2User(UserInfoDto userInfoDto) {
        this.userInfoDto = userInfoDto;

    }

    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return userInfoDto.getUiRole();
            }
        });

        return collection;
    }

    @Override
    public String getName() {

        return userInfoDto.getUiId();
    }

    public String getUiNickName() {

        return userInfoDto.getUiNickname();
    }
}