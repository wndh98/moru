package com.mogumogu.moru.user.service;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Map;

public interface UserInfoService {
    UserInfoDto detailsUserInfo(String uiId);

    int modifyUserInfo(UserInfoDto userInfoDto, String uiId) throws UserNotFoundException;

    int removeUser(String uiId) throws UserNotFoundException;

    void updatePassword(String uiId, @NotBlank String currentUiPassword,
                        @NotBlank @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.") String newUiPassword) throws UserNotFoundException;
}
