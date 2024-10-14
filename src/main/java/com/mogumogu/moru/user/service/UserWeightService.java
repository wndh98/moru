package com.mogumogu.moru.user.service;

import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;

import java.util.List;

public interface UserWeightService {

    int saveUserWeight(UserWeightDto userWeightDto, String uiId) throws UserNotFoundException;

    List<UserWeightDto> listUserWeight(String uiId);
}