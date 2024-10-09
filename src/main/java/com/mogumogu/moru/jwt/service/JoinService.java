package com.mogumogu.moru.jwt.service;

import com.mogumogu.moru.jwt.dto.JWTUserInfoDto;
import com.mogumogu.moru.jwt.entity.JWTUserEntity;
import com.mogumogu.moru.jwt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JWTUserInfoDto JWTUserInfoDto) {

        String uiId = JWTUserInfoDto.getUiId();
        String uiPassword = JWTUserInfoDto.getUiPassword();
        String uiNickname = JWTUserInfoDto.getUiNickname();
        String uiEmail = JWTUserInfoDto.getUiEmail();


        Boolean isExist = userRepository.existsByUiId(uiId);

        if (isExist) {

            return;
        }
        JWTUserEntity data = new JWTUserEntity();

        data.setUiId(uiId);
        data.setUiPassword(bCryptPasswordEncoder.encode(uiPassword));
        data.setUiNickname(uiNickname);
        data.setUiEmail(uiEmail);
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}