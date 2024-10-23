package com.mogumogu.moru.template.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateFoodDetailDTO {
    private TemplateFoodDTO template;
    private List<TemplateFoodListDTO> foodList;
}
