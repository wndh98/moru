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
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserWeightServiceImpl implements UserWeightService {

    @Autowired
    private UserWeightRepository userWeightRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    @Transactional
    public int saveUserWeight(UserWeightDto userWeightDto, String uiId) throws UserNotFoundException {

        Optional<UserWeightEntity> existingWeight = userWeightRepository.findByUiIdAndUwDate(uiId,userWeightDto.getUwDate());
        userWeightDto.setUiId(uiId);
        System.out.println(existingWeight);
        if (existingWeight.isPresent()) {
            UserWeightEntity weightToUpdate = existingWeight.get();
            weightToUpdate.setUwWeight(userWeightDto.getUwWeight());
            weightToUpdate.setUwMuscle(userWeightDto.getUwMuscle());
            weightToUpdate.setUwBodyFat(userWeightDto.getUwBodyFat());
            weightToUpdate.setUwDate(userWeightDto.getUwDate());
        } else {
            userWeightRepository.save(UserWeightEntity.toEntity(userWeightDto));
        }
        return 1;
    }

    @Override
    public List<List<UserWeightDto>> getWeeklyGroupedData(String uiId) throws UserNotFoundException {
        List<UserWeightEntity> allData = userWeightRepository.findAllByUiIdOrderByUwDateAsc(uiId);

        Map<LocalDate, List<UserWeightEntity>> groupedData = allData.stream()
                .collect(Collectors.groupingBy(entity ->
                        entity.getUwDate().withDayOfMonth(1).plusWeeks(
                                entity.getUwDate().getDayOfMonth() / 7
                        )
                ));
        List<List<UserWeightDto>> result = groupedData.values().stream()
                .map(entities -> entities.stream()
                        .map(UserWeightDto::toDto) // static 메서드 호출
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public void removeUserWeight(String uiId, Integer uwNum) throws UserNotFoundException {
        userInfoRepository.findByUiId(uiId).orElseThrow(UserNotFoundException::new);
        userWeightRepository.removeUserWeight(uiId, uwNum);
    }
}

