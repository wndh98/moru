package com.mogumogu.moru.user.service;

import com.mogumogu.moru.jwt.repository.UserInfoRepository;
import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JoinService(UserInfoRepository userInfoRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public int joinProcess(UserInfoDto userInfoDto) {

        String uiId = userInfoDto.getUiId();
        String uiPassword = userInfoDto.getUiPassword();
        String uiNickname = userInfoDto.getUiNickname();

        Boolean uiIdIsExist = userInfoRepository.existsByUiId(uiId);
        Boolean uiNicknameIsExist = userInfoRepository.existsByUiNickname(uiNickname);
        Boolean uiEmailIsExist = userInfoRepository.existsByUiEmail(uiNickname);

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

        userInfoRepository.save(data);
        return 1;
    }
}