package com.mogumogu.moru.calendar.controller;

import com.mogumogu.moru.calendar.dto.UserActiviteCalendarDTO;
import com.mogumogu.moru.calendar.service.UserActiviteCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserActiviteCalendarController {
    @Autowired
    UserActiviteCalendarService userActiviteCalendarService;

    @PostMapping("/userActiviteCalendar")
    public int addUserActiviteCalendar(@RequestBody UserActiviteCalendarDTO userActiviteCalendarDTO){
        int result = 0;

        result = userActiviteCalendarService.addUserActiviteCalendar(userActiviteCalendarDTO);
        return result;
    }

    @PostMapping("/userActiviteCalendar/{UAC_NUM}/{UAC_DATE}")
    public int saveUserActiviteCalendar(@RequestBody UserActiviteCalendarDTO userActiviteCalendarDTO){
        int result = 0;
         result = userActiviteCalendarService.saveUserActiviteCalendar(userActiviteCalendarDTO);
         return result;
    }
    @GetMapping("/userActiviteCalendar")
    public List<UserActiviteCalendarDTO> listUserActiviteCalendar(@RequestBody UserActiviteCalendarDTO userActiviteCalendarDTO){
        List<UserActiviteCalendarDTO> list = new ArrayList<>();
        list = userActiviteCalendarService.listUserActiviteCalendar(userActiviteCalendarDTO);
        return list;
    }


}
