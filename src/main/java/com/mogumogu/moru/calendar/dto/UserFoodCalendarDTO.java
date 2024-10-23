package com.mogumogu.moru.calendar.dto;

import com.mogumogu.moru.calendar.entity.UserFoodCalendar;
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
public class UserFoodCalendarDTO {
    private Integer ufcNum;
    private String uiId;
    private Date ufcDate;
    private List<String> ufcName;
    private List<Integer> ufcKcal;
    private List<Integer> ufcCarbs;
    private List<Integer> ufcProtein;
    private List<Integer> ufcFat;
    private List<Integer> ufcGram;
    private List<Integer> deleteList;

    public static UserFoodCalendarDTO toDTO(UserFoodCalendar UserFoodCalendar){
        return UserFoodCalendarDTO.builder()
                .ufcNum(UserFoodCalendar.getUfcNum())
                .uiId(UserFoodCalendar.getUiId())
                .ufcDate(UserFoodCalendar.getUfcDate())
                .ufcName(UserFoodCalendar.getUfcName())
                .ufcKcal(UserFoodCalendar.getUfcKcal())
                .ufcCarbs(UserFoodCalendar.getUfcCarbs())
                .ufcProtein(UserFoodCalendar.getUfcProtein())
                .ufcFat(UserFoodCalendar.getUfcFat())
                .ufcGram(UserFoodCalendar.getUfcGram())
                .build();
    }
}
