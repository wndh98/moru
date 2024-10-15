package com.mogumogu.moru.user.controller;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.user.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailSendService emailSendService;

    @PostMapping("/login/email")
    public Map<String, String> mailSend(@RequestBody UserInfoDto userInfoDto) {
        String code = emailSendService.joinEmail(userInfoDto.getUiEmail());

        // response를 JSON 문자열으로 반환
        Map<String, String> response = new HashMap<>();
        response.put("code", code);

        return response;
    }
}
