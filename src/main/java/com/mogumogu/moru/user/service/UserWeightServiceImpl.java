package com.mogumogu.moru.user.service;

import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWeightServiceImpl implements UserWeightService {

    @Override
    public int saveUserWeight(UserWeightDto userWeightDto, String uiId) throws UserNotFoundException {
        return 0;
    }

    @Override
    public List<UserWeightDto> listUserWeight(String uiId) {
        return List.of();
    }
}
