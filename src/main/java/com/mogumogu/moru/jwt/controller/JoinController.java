package com.mogumogu.moru.jwt.controller;

import com.mogumogu.moru.jwt.dto.JWTUserInfoDto;
import com.mogumogu.moru.jwt.service.JoinService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/users")
    public String JoinProcess(JWTUserInfoDto JWTUserInfoDto) {

        joinService.joinProcess(JWTUserInfoDto);

        return "redirect:/login";
    }
}
