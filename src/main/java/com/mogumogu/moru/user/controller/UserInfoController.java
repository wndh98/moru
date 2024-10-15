package com.mogumogu.moru.user.controller;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.service.UserInfoService;
import com.mogumogu.moru.user.service.UserWeightService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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

            //token 받아오기
            Map<String, String> tokens = userInfoService.Token(uiId);
            String AccessToken = tokens.get("access_token");

            //authorization 삭제하기
//            userInfoService.unlink(AccessToken);

            // 사용자 정보 db/서버에서 삭제하기
            userInfoService.removeUser(uiId);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}