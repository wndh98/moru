package com.mogumogu.moru.user.service;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;

public interface UserInfoService {
    UserInfoDto detailsUserInfo(String uiId);

    int modifyUserInfo(UserInfoDto userInfoDto, String uiId) throws UserNotFoundException;

    int removeUser(String uiId) throws UserNotFoundException;
}
