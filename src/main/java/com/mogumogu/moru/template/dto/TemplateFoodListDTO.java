package com.mogumogu.moru.template.dto;

import com.mogumogu.moru.template.entity.TemplateFoodList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateFoodListDTO {
    private Integer tflNum;
    private Integer tfNum;
    private List<String> tflName;
    private List<Integer> tflKcal;
    private List<Integer> tflCarbs;
    private List<Integer> tflProtein;
    private List<Integer> tflFat;
    private List<Integer> tflGram;

    public static TemplateFoodList toDTO(TemplateFoodListDTO templateFoodListDTO){
        return TemplateFoodList.builder()
                .tflNum(templateFoodListDTO.getTflNum())
                .tfNum(templateFoodListDTO.getTfNum())
                .tflName(templateFoodListDTO.getTflName())
                .tflKcal(templateFoodListDTO.getTflKcal())
                .tflCarbs(templateFoodListDTO.getTflCarbs())
                .tflProtein(templateFoodListDTO.getTflProtein())
                .tflFat(templateFoodListDTO.getTflFat())
                .tflGram(templateFoodListDTO.getTflGram())
                .build();
    }
}
