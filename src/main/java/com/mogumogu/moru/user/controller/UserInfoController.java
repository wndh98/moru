package com.mogumogu.moru.user.controller;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.service.BlacklistService;
import com.mogumogu.moru.user.dto.UpdatePasswordReq;
import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.service.UserInfoService;
import com.mogumogu.moru.user.service.UserWeightService;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    BlacklistService addToBlacklist;

    @GetMapping("/users")
    public UserInfoDto detailsUserInfo(Authentication authentication) {
        String uiId = authentication.getName();
        System.out.println(uiId+"uiId");
        UserInfoDto userInfoDTO = userInfoService.detailsUserInfo(uiId);
        System.out.println(userInfoDTO+"Userdto");

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
    public int removeUser(HttpServletRequest request, Authentication authentication) {
        String uiId = authentication.getName();
        int result = 0;
        try {
            result = userInfoService.removeUser(uiId);
            String token = request.getHeader("access").substring(7); // "Bearer " 제외
            addToBlacklist.addToBlacklist(token);

            // 사용자 정보 db/서버에서 삭제하기
            userInfoService.removeUser(uiId);

        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @PatchMapping("users/password")
    public void updatePassword(@Validated @RequestBody UpdatePasswordReq updatePasswordReq,
                               Authentication authentication) throws UserNotFoundException {
        String uiId = authentication.getName();
        userInfoService.updatePassword(uiId, updatePasswordReq.getCurrentUiPassword(),
                updatePasswordReq.getNewUiPassword());
    }
}