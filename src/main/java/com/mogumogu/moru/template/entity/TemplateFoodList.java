package com.mogumogu.moru.template.entity;

import com.mogumogu.moru.template.dto.TemplateFoodListDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TEMPLATE_FOOD_LIST_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateFoodList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tflNum;
    private Integer tfNum;
    private List<String> tflName;
    private List<Integer> tflKcal;
    private List<Integer> tflCarbs;
    private List<Integer> tflProtein;
    private List<Integer> tflFat;
    private List<Integer> tflGram;

    public static TemplateFoodList toEntity(TemplateFoodListDTO templateFoodListDTO){
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
