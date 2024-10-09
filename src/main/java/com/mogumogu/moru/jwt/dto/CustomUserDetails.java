package com.mogumogu.moru.jwt.dto;

import com.mogumogu.moru.jwt.entity.JWTUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final JWTUserEntity JWTUserEntity;

    public CustomUserDetails(JWTUserEntity JWTUserEntity) {

        this.JWTUserEntity = JWTUserEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return JWTUserEntity.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {

        return JWTUserEntity.getUiPassword();
    }

    @Override
    public String getUsername() {

        return JWTUserEntity.getUiId();
    }

    public String getUiNickname(){
        return JWTUserEntity.getUiNickname();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}