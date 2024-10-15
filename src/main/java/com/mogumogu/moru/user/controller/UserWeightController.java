package com.mogumogu.moru.user.controller;

import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.service.UserWeightService;
import lombok.RequiredArgsConstructor;
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
        String uiId = authentication.getName();
        int result = 0;
        try {
            result = userWeightService.saveUserWeight(userWeightDto, uiId);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @GetMapping("/userWeight")
    public List<UserWeightDto> listUserWeightAndWeek(Authentication authentication, @RequestParam LocalDate weekStart) throws UserNotFoundException {
        String uiId = authentication.getName();
        return userWeightService.listUserWeightAndWeek(uiId,weekStart);
    }

    @DeleteMapping("/userWeight")
    public List<UserWeightDto> removeUserWeight(Authentication authentication,int uwNum) throws UserNotFoundException {
        String uiId = authentication.getName();
        return userWeightService.removeUserWeight(uiId,uwNum);
    }
}
