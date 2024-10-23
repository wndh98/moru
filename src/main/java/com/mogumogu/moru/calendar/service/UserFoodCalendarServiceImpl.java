package com.mogumogu.moru.calendar.service;

import com.mogumogu.moru.calendar.dto.UserFoodCalendarDTO;
import com.mogumogu.moru.calendar.entity.UserActiviteCalendar;
import com.mogumogu.moru.calendar.entity.UserFoodCalendar;
import com.mogumogu.moru.calendar.repository.UserFoodCalendarRepository;
import com.mogumogu.moru.template.dto.TemplateActiviteDTO;
import com.mogumogu.moru.template.entity.TemplateActivite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFoodCalendarServiceImpl implements UserFoodCalendarService{
    @Autowired
    UserFoodCalendarRepository userFoodCalendarRepository;

    public int addUserFoodCalendar(UserFoodCalendarDTO userFoodCalendarDTO) {
        int result = 0;

        UserFoodCalendar userFoodCalendar = UserFoodCalendar.toEntity(userFoodCalendarDTO);
        UserFoodCalendar savedFoodCalendar = userFoodCalendarRepository.save(userFoodCalendar);

        if(savedFoodCalendar.getUfcNum() != null){
            result = 1;
        }else{
            result = -1;
        }

        return result;
    }

    public int saveUserFoodCalendar(UserFoodCalendarDTO userFoodCalendarDTO) {
        int result = 0; // 초기화

        // 1. 삭제할 항목이 있는 경우 삭제
        if (userFoodCalendarDTO.getDeleteList() != null && !userFoodCalendarDTO.getDeleteList().isEmpty()) {
            for (Integer id : userFoodCalendarDTO.getDeleteList()) {
                userFoodCalendarRepository.deleteById(id);
            }
        }

        // 2. ufcNum이 있는 경우 삭제
        if (userFoodCalendarDTO.getUfcNum() != null) {
            userFoodCalendarRepository.deleteById(userFoodCalendarDTO.getUfcNum());
        }

        // 3. ufcNum과 ufcGram 리스트가 모두 유효한지 검사
        if (userFoodCalendarDTO.getUfcGram() != null && userFoodCalendarDTO.getUfcGram().size() > 0) {
            for (int i = 0; i < userFoodCalendarDTO.getUfcGram().size(); i++) {
                // 반복문에서 각 데이터에 맞게 UserActiviteCalendar 객체 생성
                UserFoodCalendar newRecord = UserFoodCalendar.builder()
                        .ufcNum(userFoodCalendarDTO.getUfcNum()) // DTO의 ufcNum을 사용
                        .ufcDate(userFoodCalendarDTO.getUfcDate()) // 필요한 필드 추가
                        .ufcName(userFoodCalendarDTO.getUfcName()) // 필요한 필드 추가
                        .ufcGram(Collections.singletonList(userFoodCalendarDTO.getUfcGram().get(i))) // 리스트에서 가져오기
                        .build();

                // 4. 저장 후 결과 확인
                UserFoodCalendar savedUserFoodCalendar = userFoodCalendarRepository.save(newRecord);
                if (savedUserFoodCalendar.getUfcNum() != null) {
                    result = 1; // 저장 성공
                } else {
                    result = -1; // 저장 실패
                    break; // 저장 실패 시 반복문 종료
                }
            }
        } else {
            result = -1; // 유효한 데이터가 없을 경우
        }

        return result;
    }

    public List<UserFoodCalendarDTO> listUserFoodCalendar(UserFoodCalendarDTO userFoodCalendarDTO) {
        String uiId = userFoodCalendarDTO.getUiId();
        Date ufcDate = userFoodCalendarDTO.getUfcDate();
        List<UserFoodCalendar> list = userFoodCalendarRepository.findByUiIdUfcDate(uiId,ufcDate);

        return list.stream()
                .map(UserFoodCalendarDTO::toDTO)
                .collect(Collectors.toList());
    }
}
