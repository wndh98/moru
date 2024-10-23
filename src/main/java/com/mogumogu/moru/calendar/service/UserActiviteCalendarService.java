package com.mogumogu.moru.calendar.service;

import com.mogumogu.moru.calendar.dto.UserActiviteCalendarDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserActiviteCalendarService {
    int addUserActiviteCalendar(UserActiviteCalendarDTO userActiviteCalendarDTO);

    List<UserActiviteCalendarDTO> listUserActiviteCalendar(UserActiviteCalendarDTO userActiviteCalendarDTO);

    int saveUserActiviteCalendar(UserActiviteCalendarDTO userActiviteCalendarDTO);
}
