package com.mogumogu.moru.jwt.service;

import com.mogumogu.moru.jwt.repository.UserRepository;
import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
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

    public void joinProcess(UserInfoDto UserDto) {

        String uiId = UserDto.getUiId();
        String uiPassword = UserDto.getUiPassword();
//        String uiNickname = UserDto.getUiNickname();

        Boolean isExist = userRepository.existsByUiId(uiId);

        if (isExist) {

            return;
        }
        UserInfoEntity data = new UserInfoEntity();

        data.setUiId(uiId);
        data.setUiPassword(bCryptPasswordEncoder.encode(uiPassword));
//        data.setUiNickname(uiNickname);
        data.setUiRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}