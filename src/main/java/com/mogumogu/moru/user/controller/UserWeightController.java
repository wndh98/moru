package com.mogumogu.moru.user.controller;

import ch.qos.logback.classic.Logger;
import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.entity.UserWeightEntity;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.service.UserWeightService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserWeightController {
    @Autowired
    UserWeightService userWeightService;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserWeightController.class);

    @PostMapping("/userWeight")
    public ResponseEntity<Integer> saveUserWeight(@RequestBody UserWeightDto userWeightDto, Authentication authentication) {
        String uiId = authentication.getName();

        if (userWeightDto == null || userWeightDto.getUwWeight() <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            int result = userWeightService.saveUserWeight(userWeightDto, uiId);
            return ResponseEntity.ok(result);
        } catch (UserNotFoundException e) {

            logger.error("User not found: {}", uiId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            logger.error("An error occurred while saving user weight", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/userWeight")
    public List<UserWeightEntity> listUserWeight(Authentication authentication,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        String uiId = authentication.getName();
        try {
            return userWeightService.findAllByUiIdAndUwDateBetween(uiId, startDate, endDate);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/userWeight")
    public void removeUserWeight(Authentication authentication, @RequestParam(required = false) Integer uwNum) {
        String uiId = authentication.getName();
        try {
            userWeightService.removeUserWeight(uiId, uwNum);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
