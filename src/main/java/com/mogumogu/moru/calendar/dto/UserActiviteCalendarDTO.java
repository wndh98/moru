package com.mogumogu.moru.calendar.dto;

import com.mogumogu.moru.calendar.entity.UserActiviteCalendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserActiviteCalendarDTO {
    private Integer uacNum;
    private String uiId;
    private List<Integer> uaNum;
    private Date uacDate;
    private List<Integer> uacTime;
    private List<Integer> deleteList;

    public UserActiviteCalendarDTO toDTO(UserActiviteCalendar userActiviteCalendar){
        return  UserActiviteCalendarDTO.builder()
                .uacNum(userActiviteCalendar.getUacNum())
                .uiId(userActiviteCalendar.getUiId())
                .uaNum(userActiviteCalendar.getUaNum())
                .uacDate(userActiviteCalendar.getUacDate())
                .uacTime(userActiviteCalendar.getUacTime())
                .build();
    }
}
