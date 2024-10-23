package com.mogumogu.moru.template.entity;

import com.mogumogu.moru.template.dto.TemplateActiviteListDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TEMPLATE_ACTIVITE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateActivite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taNum;
    private String uiId;
    private String taName;

    public static TemplateActivite toEntity(TemplateActiviteListDTO templateActiviteDTO){
        return TemplateActivite.builder()
                .taNum(templateActiviteDTO.getTaNum())
                .uiId(templateActiviteDTO.getUiId())
                .taName(templateActiviteDTO.getTaName())
                .build();
    }
}
