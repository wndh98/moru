package com.mogumogu.moru.user.entity;

import com.mogumogu.moru.user.dto.UserWeightDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "USER_WEIGHT_TB")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWeightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UW_NUM", nullable = false)
    private int uwNum;
    private String uiId;
    private Date uwDate;
    private int uwWeight;
    private int uwBodyFat;
    private int uwMuscle;

    public static UserWeightEntity toEntity(UserWeightDto userWeightDto){
        return UserWeightEntity.builder()
                .uwNum(userWeightDto.getUwNum())
                .uiId(userWeightDto.getUiId())
                .uwDate(userWeightDto.getUwDate())
                .uwWeight(userWeightDto.getUwWeight())
                .uwBodyFat(userWeightDto.getUwWeight())
                .uwMuscle(userWeightDto.getUwWeight())
                .build();
    }
}
