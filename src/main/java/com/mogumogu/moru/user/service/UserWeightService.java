package com.mogumogu.moru.user.service;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.entity.UserWeightEntity;
import com.mogumogu.moru.user.exception.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface UserWeightService {

    int saveUserWeight(UserWeightDto userWeightDto, String uiId) throws UserNotFoundException;

    List<List<UserWeightDto>> getWeeklyGroupedData(String uiId) throws UserNotFoundException;

    void removeUserWeight(String uiId, Integer uwNum) throws UserNotFoundException;
}
