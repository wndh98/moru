package com.mogumogu.moru.calendar.entity;

import com.mogumogu.moru.calendar.dto.UserActiviteCalendarDTO;
import com.mogumogu.moru.calendar.dto.UserFoodCalendarDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "USER_FOOD_CALENDAR_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFoodCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public static UserFoodCalendar toEntity(UserFoodCalendarDTO userFoodCalendarDTO){
        return UserFoodCalendar.builder()
                .ufcNum(userFoodCalendarDTO.getUfcNum())
                .uiId(userFoodCalendarDTO.getUiId())
                .ufcDate(userFoodCalendarDTO.getUfcDate())
                .ufcName(userFoodCalendarDTO.getUfcName())
                .ufcKcal(userFoodCalendarDTO.getUfcKcal())
                .ufcCarbs(userFoodCalendarDTO.getUfcCarbs())
                .ufcProtein(userFoodCalendarDTO.getUfcProtein())
                .ufcFat(userFoodCalendarDTO.getUfcFat())
                .ufcGram(userFoodCalendarDTO.getUfcGram())
                .build();
    }
}
