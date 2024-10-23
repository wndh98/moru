package com.mogumogu.moru.template.entity;

import com.mogumogu.moru.template.dto.TemplateActiviteListDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TEMPLATE_ACTIVITE_LIST_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateActiviteList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer talNum;
    private Integer taNum;
    private List<Integer> uaNum;
    private List<Integer> talTime;

    public static TemplateActiviteList toEntity(TemplateActiviteListDTO templateActiviteListDTO){
        return TemplateActiviteList.builder()
                .talNum(templateActiviteListDTO.getTalNum())
                .taNum(templateActiviteListDTO.getTaNum())
                .uaNum(templateActiviteListDTO.getUaNum())
                .talTime(templateActiviteListDTO.getTalTime())
                .build();
    }
}
