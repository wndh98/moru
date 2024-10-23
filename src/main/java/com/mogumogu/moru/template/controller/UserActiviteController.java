package com.mogumogu.moru.template.controller;

import com.mogumogu.moru.template.dto.UserActiviteDTO;
import com.mogumogu.moru.template.service.UserActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserActiviteController {
    @Autowired
    UserActiviteService userActiviteService;

    @PostMapping("/userActivite")
    public int addUserActivite(@RequestBody UserActiviteDTO userActiviteDTO){
        int result = 0;

        result = userActiviteService.addUserActivite(userActiviteDTO);
        return result;
    }

    @GetMapping("/userActivite")
    public List<UserActiviteDTO> listUserActivite(){
        List<UserActiviteDTO> list = new ArrayList<>();
        list = userActiviteService.listUserActivite();
        return list;
    }
}
