package com.mogumogu.moru.jwt.service;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserEntity;
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

    public void joinProcess(UserInfoDto userInfoDto) {

        String uiId = userInfoDto.getUiId();
        String uiPassword = userInfoDto.getUiPassword();
        String uiNickname = userInfoDto.getUiNickname();
        String uiEmail = userInfoDto.getUiEmail();


        Boolean isExist = userRepository.existsByUiId(uiId);

        if (isExist) {

            return;
        }
        UserEntity data = new UserEntity();

        data.setUiId(uiId);
        data.setUiPassword(bCryptPasswordEncoder.encode(uiPassword));
        data.setUiNickname(uiNickname);
        data.setUiEmail(uiEmail);
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}