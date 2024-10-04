package com.mogumogu.moru.jwt.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MainContoller {

    @GetMapping("/")
    public String mainP() {

        String uiId = SecurityContextHolder.getContext().getAuthentication().getName();
        return "Main Controller" + uiId;
    }

}
