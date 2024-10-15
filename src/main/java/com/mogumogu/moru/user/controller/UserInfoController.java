package com.mogumogu.moru.user.controller;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.service.UserInfoService;
import com.mogumogu.moru.user.service.UserWeightService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserInfoController {

    UserInfoService userInfoService;

    @GetMapping("/users")
    public UserInfoDto detailsUserInfo(Authentication authentication) {
        String uiId = authentication.getName();
        UserInfoDto userInfoDTO = userInfoService.detailsUserInfo(uiId);
        return userInfoDTO;
    }

    @PutMapping("/users")
    public int modifyUserInfo(@RequestBody UserInfoDto userInfoDto, Authentication authentication) {
        String uiId = authentication.getName();
        int result = 0;
        try {
            result = userInfoService.modifyUserInfo(userInfoDto, uiId);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @DeleteMapping("/users")
    public int removeUser(Authentication authentication) {
        String uiId = authentication.getName();
        int result = 0;
        try {
            result = userInfoService.removeUser(uiId);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
