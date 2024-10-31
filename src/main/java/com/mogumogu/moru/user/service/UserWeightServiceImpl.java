package com.mogumogu.moru.user.service;

import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.entity.UserWeightEntity;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.repository.UserInfoRepository;
import com.mogumogu.moru.user.repository.UserWeightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserWeightServiceImpl implements UserWeightService {

    @Autowired
    private UserWeightRepository userWeightRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    @Transactional
    public int saveUserWeight(UserWeightDto userWeightDto, String uiId) throws UserNotFoundException {

        userInfoRepository.findByUiId(uiId).orElseThrow(UserNotFoundException::new);
        Optional<UserWeightEntity> existingWeight = userWeightRepository.findByUwDateAndUiId(userWeightDto.getUwDate(), uiId);
        userWeightDto.setUiId(uiId);
        if (existingWeight.isPresent()) {
            UserWeightEntity weightToUpdate = existingWeight.get();
            weightToUpdate.setUwWeight(userWeightDto.getUwWeight());
            weightToUpdate.setUwMuscle(userWeightDto.getUwMuscle());
            weightToUpdate.setUwBodyFat(userWeightDto.getUwBodyFat());
            weightToUpdate.setUwDate(userWeightDto.getUwDate());
            userWeightRepository.save(weightToUpdate);
        } else {
            userWeightRepository.save(UserWeightEntity.toEntity(userWeightDto));
        }
        return 1;
    }

    @Override
    @Transactional
    public List<UserWeightEntity> findAllByUiIdAndUwDateBetween(String uiId, LocalDate startDate, LocalDate endDate) throws UserNotFoundException {
        try {
            List<UserWeightEntity> results = userWeightRepository.findAllByUiIdAndUwDateBetween(uiId, startDate, endDate);
            if (results.isEmpty()) {
                throw new UserNotFoundException();
            }
            return results;
        } catch (Exception exception) {
            throw new UserNotFoundException();
        }
    }


    @Override
    @Transactional
    public void removeUserWeight(String uiId, Integer uwNum) throws UserNotFoundException {
        userInfoRepository.findByUiId(uiId).orElseThrow(UserNotFoundException::new);
        userWeightRepository.removeUserWeight(uiId, uwNum);
    }
}

