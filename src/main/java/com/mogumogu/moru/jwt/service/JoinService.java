package com.mogumogu.moru.jwt.service;

import com.mogumogu.moru.jwt.repository.UserRepository;
import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public int joinProcess(UserInfoDto userInfoDto) {

        String uiId = userInfoDto.getUiId();
        String uiPassword = userInfoDto.getUiPassword();
        String uiNickname = userInfoDto.getUiNickname();

        Boolean uiIdIsExist = userRepository.existsByUiId(uiId);
        Boolean uiNicknameIsExist = userRepository.existsByUiNickname(uiNickname);
        Boolean uiEmailIsExist = userRepository.existsByUiEmail(uiNickname);

        if (uiIdIsExist) {

            return -1;
        }
        if (uiNicknameIsExist) {

            return -2;
        }
        if(uiEmailIsExist){

            return -3;
        }

        UserInfoEntity data;
        data = UserInfoEntity.toEntity(userInfoDto);
        data.setUiPassword(bCryptPasswordEncoder.encode(uiPassword));
        data.setUiRole("ROLE_USER");
        data.setUiPoint(0);

        userRepository.save(data);
        System.out.println(data+" join data");

        return 1;
    }
}