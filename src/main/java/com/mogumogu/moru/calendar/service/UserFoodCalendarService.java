package com.mogumogu.moru.calendar.service;

import com.mogumogu.moru.calendar.dto.UserFoodCalendarDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserFoodCalendarService {
    int addUserFoodCalendar(UserFoodCalendarDTO userFoodCalendarDTO);

    int saveUserFoodCalendar(UserFoodCalendarDTO userFoodCalendarDTO);

    List<UserFoodCalendarDTO> listUserFoodCalendar(UserFoodCalendarDTO userFoodCalendarDTO);
}
