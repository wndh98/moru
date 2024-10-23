package com.mogumogu.moru.template.dto;

import com.mogumogu.moru.template.entity.TemplateActiviteList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateActiviteListDTO {
    private Integer talNum;
    private Integer taNum;
    private List<Integer> uaNum;
    private List<Integer> talTime;

    public static TemplateActiviteListDTO toDTO(TemplateActiviteList templateActiviteList){
        return TemplateActiviteListDTO.builder()
                .talNum(templateActiviteList.getTaNum())
                .taNum(templateActiviteList.getTaNum())
                .uaNum(templateActiviteList.getUaNum())
                .talTime(templateActiviteList.getTalTime())
                .build();
    }


}
