package com.mogumogu.moru.calendar.entity;

import com.mogumogu.moru.calendar.dto.UserActiviteCalendarDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "USER_ACTIVITE_CALENDAR_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserActiviteCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uacNum;
    private String uiId;
    private List<Integer> uaNum;
    private Date uacDate;
    private List<Integer> uacTime;

    public static UserActiviteCalendar toEntity(UserActiviteCalendarDTO userActiviteCalendarDTO){
        return UserActiviteCalendar.builder()
                .uacNum(userActiviteCalendarDTO.getUacNum())
                .uiId(userActiviteCalendarDTO.getUiId())
                .uaNum(userActiviteCalendarDTO.getUaNum())
                .uacDate(userActiviteCalendarDTO.getUacDate())
                .uacTime(userActiviteCalendarDTO.getUacTime())
                .build();
    }
}
