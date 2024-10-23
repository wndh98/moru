package com.mogumogu.moru.calendar.service;

import com.mogumogu.moru.calendar.dto.UserActiviteCalendarDTO;
import com.mogumogu.moru.calendar.entity.UserActiviteCalendar;
import com.mogumogu.moru.calendar.repository.UserActiviteCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserActiviteCalendarServiceImpl implements UserActiviteCalendarService{
    @Autowired
    UserActiviteCalendarRepository userActiviteCalendarRepository;

    public int addUserActiviteCalendar(UserActiviteCalendarDTO userActiviteCalendarDTO) {
        int result = 0;
        UserActiviteCalendar userActiviteCalendar = UserActiviteCalendar.toEntity(userActiviteCalendarDTO);
        UserActiviteCalendar savedUserActiviteCalendar = userActiviteCalendarRepository.save(userActiviteCalendar);
        if (savedUserActiviteCalendar.getUacNum() != null) {
            result = 1;
        }
        return result;
    }

    public List<UserActiviteCalendarDTO> listUserActiviteCalendar(UserActiviteCalendarDTO userActiviteCalendarDTO) {
        List<UserActiviteCalendar> userActiviteCalendar = userActiviteCalendarRepository
                .findByUiIdAndUacDate(String.valueOf(Integer.valueOf(userActiviteCalendarDTO.getUiId())),
                        userActiviteCalendarDTO.getUacDate());

        // 조회된 데이터를 DTO로 변환하여 반환
        return userActiviteCalendar.stream()
                .map(userActiviteCalendarDTO::toDTO)
                .collect(Collectors.toList());
    }

    public int saveUserActiviteCalendar(UserActiviteCalendarDTO userActiviteCalendarDTO) {
        int result = 0;

        if(userActiviteCalendarDTO.getDeleteList() != null && !userActiviteCalendarDTO.getDeleteList().isEmpty()){
            for(Integer id : userActiviteCalendarDTO.getDeleteList()){
                userActiviteCalendarRepository.deleteById(id);
            }
        }

        if(userActiviteCalendarDTO.getUacNum() != null){
            userActiviteCalendarRepository.deleteById(userActiviteCalendarDTO.getUacNum());
        }

        if (userActiviteCalendarDTO.getUaNum() != null && userActiviteCalendarDTO.getUacTime() != null
                && userActiviteCalendarDTO.getUaNum().size() == userActiviteCalendarDTO.getUacTime().size()) {
            for (int i = 0; i < userActiviteCalendarDTO.getUaNum().size(); i++) {
                UserActiviteCalendar newRecord = UserActiviteCalendar.toEntity(userActiviteCalendarDTO);
                UserActiviteCalendar savedUserActiviteCalendar = userActiviteCalendarRepository.save(newRecord);
                if(savedUserActiviteCalendar.getUacNum()!=null){
                    result = 1;
                }else{
                    result = -1;
                }
            }

        }

        return result;
    }
}
