package com.mogumogu.moru.user.service;

import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.repository.UserWeightRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserWeightServiceImpl implements UserWeightService {

    private UserWeightRepository userWeightRepository;

    @Override
    public int saveUserWeight(UserWeightDto userWeightDto, String uiId) throws UserNotFoundException {


        return 0;
    }

    @Override
    public List<UserWeightDto> listUserWeightAndWeek(String uiId, LocalDate weekStart) {

        // 주 시작일을 일요일로 설정
        LocalDate startOfWeek = weekStart.with(DayOfWeek.SUNDAY);
        // 주 종료일을 토요일로 설정
        LocalDate endOfWeek = startOfWeek.with(DayOfWeek.SATURDAY).plusDays(1); // 다음 날로 설정 (inclusive)

        return userWeightRepository.listUserWeightAndWeek(uiId, startOfWeek.atStartOfDay(), endOfWeek.atStartOfDay());
    }

    @Override
    public List<UserWeightDto> removeUserWeight(String uiId, int uwNum) throws UserNotFoundException {

        return userWeightRepository.removeUserWeight(uiId,uwNum);
    }
}

