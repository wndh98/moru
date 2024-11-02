package com.mogumogu.moru.user.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer uwNum;
    private String uiId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate uwDate;
    private Integer uwWeight;
    private Integer uwBodyFat;
    private Integer uwMuscle;

    public static UserWeightDto toDto(UserWeightEntity userWeightEntity){
        return UserWeightDto.builder()
                .uwNum(userWeightEntity.getUwNum())
                .uiId(userWeightEntity.getUiId())
                .uwDate(userWeightEntity.getUwDate())
                .uwWeight(userWeightEntity.getUwWeight())
                .uwBodyFat(userWeightEntity.getUwBodyFat())
                .uwMuscle(userWeightEntity.getUwMuscle())
                .build();
    }
}
