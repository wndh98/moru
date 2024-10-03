package com.mogumogu.moru.user.controller;

import com.mogumogu.moru.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserInfoService us;
}
