package com.mogumogu.moru.template.service;

import com.mogumogu.moru.template.dto.TemplateActiviteDetailDTO;
import com.mogumogu.moru.template.dto.TemplateFoodDTO;
import com.mogumogu.moru.template.dto.TemplateFoodDetailDTO;
import com.mogumogu.moru.template.dto.TemplateFoodListDTO;
import com.mogumogu.moru.template.entity.TemplateFood;
import com.mogumogu.moru.template.projection.TemplateFoodProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TemplateFoodService {
    int addUserFoodTemplate(TemplateFoodDTO templateFoodDTO, TemplateFoodListDTO templateFoodListDTO);

    int removeUserFoodTemplate(String uiId, Integer tfNum);

    List<TemplateFoodProjection> listUserFoodTemplate(String uiId);

    TemplateFoodDetailDTO detailUserFoodTemplate(Integer tfNum);
}
