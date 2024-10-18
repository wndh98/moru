package com.mogumogu.moru.user.service;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.entity.UserWeightEntity;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.repository.UserInfoRepository;
import com.mogumogu.moru.user.repository.UserWeightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class UserWeightServiceImpl implements UserWeightService {

    @Autowired
    private UserWeightRepository userWeightRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    @Transactional
    public int saveUserWeight(UserWeightDto userWeightDto, String uiId) throws UserNotFoundException {
        int result = 1;
        if (!Objects.equals(userWeightDto.getUiId(), uiId)) {
            result = 0;
            return result;
        }
        UserInfoEntity userInfoEntity = userInfoRepository.findByUiId(uiId).orElseThrow(UserNotFoundException::new);
        userWeightRepository.save(UserWeightEntity.toEntity(userWeightDto));
        return result;
    }

    @Override
    public List<UserWeightDto> listUserWeightAndWeek(String uiId, LocalDate weekStart) throws UserNotFoundException {
        userInfoRepository.findByUiId(uiId).orElseThrow(UserNotFoundException::new);

        // 주 시작일을 일요일로 설정
        LocalDate startOfWeek = weekStart.with(DayOfWeek.SUNDAY);
        // 주 종료일을 토요일로 설정
        LocalDate endOfWeek = startOfWeek.with(DayOfWeek.SATURDAY); // inclusive로 설정할 경우 다음 날을 추가하지 않음

        return userWeightRepository.listUserWeightAndWeek(uiId, startOfWeek, endOfWeek);
    }

    @Override
    @Transactional
    public void removeUserWeight(String uiId, Integer uwNum) throws UserNotFoundException {
        userInfoRepository.findByUiId(uiId).orElseThrow(UserNotFoundException::new);
        userWeightRepository.removeUserWeight(uiId, uwNum);
    }
}

