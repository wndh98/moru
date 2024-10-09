package com.mogumogu.moru.jwt.dto;

import com.mogumogu.moru.user.entity.UserInfoEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final UserInfoEntity UserEntity;

    public CustomUserDetails(UserInfoEntity UserEntity) {

        this.UserEntity = UserEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return UserEntity.getUiRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {

        return UserEntity.getUiPassword();
    }

    @Override
    public String getUsername() {

        return UserEntity.getUiId();
    }

    public String getUiNickname(){
        return UserEntity.getUiNickname();
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