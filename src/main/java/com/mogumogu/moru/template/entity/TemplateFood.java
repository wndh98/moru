package com.mogumogu.moru.template.entity;

import com.mogumogu.moru.template.dto.TemplateFoodDTO;
import com.mogumogu.moru.template.dto.TemplateFoodListDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TEMPLATE_FOOD_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tfNum;
    private String uiId;
    private String tfName;

    public static TemplateFood toEntity(TemplateFoodDTO templateFoodDTO){
        return TemplateFood.builder()
                .tfNum(templateFoodDTO.getTfNum())
                .uiId(templateFoodDTO.getUiId())
                .tfName(templateFoodDTO.getTfName())
                .build();
    }
}
