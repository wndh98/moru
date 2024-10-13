package com.mogumogu.moru.user.service;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    char UserInfo_DEL = 'Y';

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserInfoDto detailsUserInfo(String uiId) {
        UserInfoEntity userInfoEntity = userInfoRepository.findByUiId(uiId).orElseThrow();
        ;
        UserInfoDto userInfoDto = UserInfoDto.toDto(userInfoEntity);
        return userInfoDto;
    }

    @Override
    public int modifyUserInfo(UserInfoDto userInfoDto, String uiId) throws UserNotFoundException {

        int result = 1;

        if (!Objects.equals(userInfoDto.getUiId(), uiId)) {
            result = 0;
            return result;
        }
        UserInfoEntity userInfoEntity = userInfoRepository.findByUiId(uiId).orElseThrow(UserNotFoundException::new);
        userInfoEntity.setUiNickname(userInfoDto.getUiPassword());
        userInfoEntity.setUiNickname(userInfoDto.getUiNickname());
        userInfoEntity.setUiEmail(userInfoDto.getUiNickname());
        userInfoEntity.setUiAge(userInfoDto.getUiAge());
        userInfoEntity.setUiGender(userInfoDto.getUiGender());
        userInfoEntity.setUiHeight(userInfoDto.getUiHeight());
        userInfoRepository.save(userInfoEntity);

        return result;
    }

    @Override
    public int removeUser(String uiId) throws UserNotFoundException {

        int result = 1;
        UserInfoEntity userInfoEntity = userInfoRepository.findByUiId(uiId).orElseThrow(UserNotFoundException::new);
        userInfoEntity.setUiDel(UserInfo_DEL);
        userInfoRepository.save(userInfoEntity);
        return result;
    }
}

