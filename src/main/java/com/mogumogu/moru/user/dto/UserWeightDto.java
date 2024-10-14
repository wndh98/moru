package com.mogumogu.moru.user.dto;
import com.mogumogu.moru.user.entity.UserWeightEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWeightDto {
    private int uwNum;
    private String uiId;
    private LocalDate uwDate;
    private int uwWeight;
    private int uwBodyFat;
    private int uwMuscle;

    public static UserWeightDto toDto(UserWeightEntity userWeightEntity){
        return UserWeightDto.builder()
                .uwNum(userWeightEntity.getUwNum())
                .uiId(userWeightEntity.getUiId())
                .uwDate(userWeightEntity.getUwDate())
                .uwWeight(userWeightEntity.getUwWeight())
                .uwBodyFat(userWeightEntity.getUwWeight())
                .uwMuscle(userWeightEntity.getUwMuscle())
                .build();
    }
}
