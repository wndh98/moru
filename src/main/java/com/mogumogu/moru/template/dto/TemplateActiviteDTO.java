package com.mogumogu.moru.template.dto;

import com.mogumogu.moru.template.entity.TemplateActivite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateActiviteDTO {
    private Integer taNum;
    private String uiId;
    private String taName;

    public static TemplateActiviteDTO toDTO(TemplateActivite templateActivite){
        return TemplateActiviteDTO.builder()
                .taNum(templateActivite.getTaNum())
                .uiId(templateActivite.getUiId())
                .taName(templateActivite.getTaName())
                .build();
    }
}
