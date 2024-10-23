package com.mogumogu.moru.template.dto;

import com.mogumogu.moru.template.entity.TemplateFood;
import com.mogumogu.moru.template.entity.UserActivite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateFoodDTO {
    private Integer tfNum;
    private String uiId;
    private String tfName;

    public static TemplateFood toDTO(TemplateFoodDTO TemplateFoodDTO){
        return TemplateFood.builder()
                .tfNum(TemplateFoodDTO.getTfNum())
                .uiId(TemplateFoodDTO.getUiId())
                .tfName(TemplateFoodDTO.getTfName())
                .build();
    }
}
