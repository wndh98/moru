package com.mogumogu.moru.user.controller;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.service.BlacklistService;
import com.mogumogu.moru.user.dto.UpdatePasswordReq;
import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.service.UserInfoService;
import com.mogumogu.moru.user.service.UserWeightService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final BlacklistService addToBlacklist;

    @GetMapping("/users")
    public UserInfoDto detailsUserInfo(Authentication authentication) {
        String uiId = authentication.getName();
        UserInfoDto userInfoDto = userInfoService.detailsUserInfo(uiId);

        return userInfoDto;
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
    public int removeUser(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String uiId = authentication.getName();
        int result = 0;
        try {
            result = userInfoService.removeUser(uiId);
            String token = request.getHeader("accesstoken");
            addToBlacklist.addToBlacklist(token);

            // 사용자 정보 db/서버에서 삭제하기
            userInfoService.removeUser(uiId);

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("urtToken")) {
                        cookie.setMaxAge(0); // 쿠키의 만료시간을 0으로 설정하여 삭제
                        cookie.setHttpOnly(true); // HttpOnly 설정
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @PatchMapping("/users")
    public void updatePassword(@Valid @RequestBody UpdatePasswordReq updatePasswordReq,
                               Authentication authentication) throws UserNotFoundException {
        String uiId = authentication.getName();
        userInfoService.updatePassword(uiId, updatePasswordReq.getUiPassword(),
                updatePasswordReq.getNewUiPassword());
    }
}