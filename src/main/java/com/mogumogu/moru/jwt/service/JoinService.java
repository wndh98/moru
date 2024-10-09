package com.mogumogu.moru.jwt.service;

import com.mogumogu.moru.jwt.dto.JWTUserInfoDto;
import com.mogumogu.moru.jwt.repository.UserRepository;
import com.mogumogu.moru.user.entity.UserInfoEntity;
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

    public void joinProcess(UserInfoEntity UserEntity) {

        String uiId = UserEntity.getUiId();
        String uiPassword = UserEntity.getUiPassword();
        String uiNickname = UserEntity.getUiNickname();
        String uiEmail = UserEntity.getUiEmail();


        Boolean isExist = userRepository.existsByUiId(uiId);

        if (isExist) {

            return;
        }
        UserInfoEntity data = new UserInfoEntity();

        data.setUiId(uiId);
        data.setUiPassword(bCryptPasswordEncoder.encode(uiPassword));
        data.setUiNickname(uiNickname);
        data.setUiEmail(uiEmail);
        data.setUiRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}