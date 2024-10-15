package com.mogumogu.moru.jwt.service;

import com.mogumogu.moru.jwt.repository.UserRepository;
import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public int joinProcess(UserInfoDto UserDto) {

        String uiId = UserDto.getUiId();
        String uiPassword = UserDto.getUiPassword();
        String uiNickname = UserDto.getUiNickname();

        Boolean isExist = userRepository.existsByUiId(uiId);

        if (isExist) {

            return 0;
        }
        UserInfoEntity data;
        data = UserInfoEntity.toEntity(UserDto);
        data.setUiPassword(bCryptPasswordEncoder.encode(uiPassword));

        userRepository.save(data);
        return 0;
    }
}