package com.mogumogu.moru.user.service;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.entity.UserWeightEntity;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.repository.UserInfoRepository;
import com.mogumogu.moru.user.repository.UserWeightRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class UserWeightServiceImpl implements UserWeightService {

    private UserWeightRepository userWeightRepository;
    private UserInfoRepository userInfoRepository;


    @Override
    public int saveUserWeight(UserWeightDto UserWeightDto,String uiId) throws UserNotFoundException {
        int result = 1;

        if (!Objects.equals(UserWeightDto.getUiId(), uiId)) {
            result = 0;
            return result;
        }
        UserWeightEntity userWeightEntity = userWeightRepository.save(UserWeightEntity.toEntity(UserWeightDto));
        return result;
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

        return userWeightRepository.removeUserWeight(uiId, uwNum);
    }
}

