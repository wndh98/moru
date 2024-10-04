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

        String userId = userInfoDto.getUiId();
        String userPassword = userInfoDto.getUiPassword();

        Boolean isExist = userRepository.existsById(userId);

        if (isExist) {

            return;
        }
        UserEntity data = new UserEntity();

        data.setUiId(userId);
        data.setUiPassword(bCryptPasswordEncoder.encode(userPassword));

        userRepository.save(data);
    }


}