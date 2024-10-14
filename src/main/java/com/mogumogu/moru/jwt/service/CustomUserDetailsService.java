package com.mogumogu.moru.jwt.service;

import com.mogumogu.moru.jwt.dto.CustomUserDetails;
import com.mogumogu.moru.jwt.repository.UserRepository;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DB에서 조회
        UserInfoEntity userData = userRepository.findByUiId(username);

        if (userData != null) {

            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(userData);
        }
        AccountContext accountContext = new AccountContext(userInfoEntity, uiRole);

        return accountContext;
    }
}