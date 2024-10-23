package com.mogumogu.moru.calendar.controller;

import com.mogumogu.moru.calendar.dto.UserFoodCalendarDTO;
import com.mogumogu.moru.calendar.service.UserFoodCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserFoodCalendarController {
    @Autowired
    UserFoodCalendarService userFoodCalendarService;

    @PostMapping("/userFoodCalendar")
    public int addUserFoodCalendar(@RequestBody UserFoodCalendarDTO userFoodCalendarDTO){
        int result = 0;
        result = userFoodCalendarService.addUserFoodCalendar(userFoodCalendarDTO);
        return result;
    }

    @PostMapping("userFoodCalendar/{UFC_DATE}")
    public int saveUserFoodCalendar(@RequestBody UserFoodCalendarDTO userFoodCalendarDTO){
        int result = 0;
        result = userFoodCalendarService.saveUserFoodCalendar(userFoodCalendarDTO);
        return result;
    }

    @GetMapping("/userFoodCalendar")
    public List<UserFoodCalendarDTO> listUserFoodCalendar(@RequestBody UserFoodCalendarDTO userFoodCalendarDTO){
        List<UserFoodCalendarDTO> list = new ArrayList<>();
        list = userFoodCalendarService.listUserFoodCalendar(userFoodCalendarDTO);
        return list;
    }
}
