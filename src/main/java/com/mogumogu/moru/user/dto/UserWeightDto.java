package com.mogumogu.moru.user.dto;
import lombok.Data;

import java.util.Date;
@Data
public class UserWeightDto {
    private int uwNum;
    private String uiId;
    private Date uwDate;
    private int uwWeight;
    private int uwBodyFat;
    private int uwMuscle;
}
