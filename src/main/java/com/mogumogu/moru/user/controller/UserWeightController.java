package com.mogumogu.moru.user.controller;

import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.service.UserWeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserWeightController {

    UserWeightService userWeightService;

    @PostMapping("/userWeight")
    public int saveUserWeight(@RequestBody UserWeightDto userWeightDto, Authentication authentication) {
        int result = 0;
        String uiId = authentication.getName();
        try {
            result = userWeightService.saveUserWeight(userWeightDto, uiId);

        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @GetMapping("/userWeight")
    public List<UserWeightDto> listUserWeightAndWeek(Authentication authentication, @RequestParam LocalDate weekStart){
        String uiId = authentication.getName();
        try {
            return userWeightService.listUserWeightAndWeek(uiId,weekStart);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/userWeight")
    public void removeUserWeight(Authentication authentication, @RequestParam(required = false) Integer uwNum) {
        String uiId = authentication.getName();
        try {
            userWeightService.removeUserWeight(uiId,uwNum);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
