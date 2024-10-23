package com.mogumogu.moru.template.service;

import com.mogumogu.moru.template.dto.*;
import com.mogumogu.moru.template.entity.TemplateActivite;
import com.mogumogu.moru.template.entity.TemplateActiviteList;
import com.mogumogu.moru.template.entity.TemplateFood;
import com.mogumogu.moru.template.entity.TemplateFoodList;
import com.mogumogu.moru.template.projection.TemplateFoodProjection;
import com.mogumogu.moru.template.repository.TemplateFoodListRepository;
import com.mogumogu.moru.template.repository.TemplateFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemplateFoodServiceImpl implements TemplateFoodService{
    @Autowired
    TemplateFoodRepository templateFoodRepository;

    @Autowired
    TemplateFoodListRepository templateFoodListRepository;

    public int addUserFoodTemplate(TemplateFoodDTO templateFoodDTO, TemplateFoodListDTO templateFoodListDTO) {
        int result = 0;

        TemplateFood templateFood = TemplateFood.toEntity(templateFoodDTO);
        TemplateFood savedTemplateFood = templateFoodRepository.save(templateFood);

        if (savedTemplateFood.getTfNum() != null) {
            result = 1;
        } else {
            return -1;
        }

        if (templateFoodListDTO.getTflName() != null && templateFoodListDTO.getTflKcal() != null
                && templateFoodListDTO.getTflCarbs() != null && templateFoodListDTO.getTflProtein() != null
                && templateFoodListDTO.getTflFat() != null && templateFoodListDTO.getTflGram() != null
                && templateFoodListDTO.getTflName().size() == templateFoodListDTO.getTflKcal().size()
                && templateFoodListDTO.getTflKcal().size() == templateFoodListDTO.getTflCarbs().size()
                && templateFoodListDTO.getTflCarbs().size() == templateFoodListDTO.getTflProtein().size()
                && templateFoodListDTO.getTflProtein().size() == templateFoodListDTO.getTflFat().size()
                && templateFoodListDTO.getTflFat().size() == templateFoodListDTO.getTflGram().size()) {

            for (int i = 0; i < templateFoodListDTO.getTflName().size(); i++) {
                TemplateFoodList templateFoodList = TemplateFoodList.builder()
                        .tfNum(savedTemplateFood.getTfNum())
                        .tflName(Collections.singletonList(templateFoodListDTO.getTflName().get(i)))
                        .tflKcal(Collections.singletonList(templateFoodListDTO.getTflKcal().get(i)))
                        .tflCarbs(Collections.singletonList(templateFoodListDTO.getTflCarbs().get(i)))
                        .tflProtein(Collections.singletonList(templateFoodListDTO.getTflProtein().get(i)))
                        .tflFat(Collections.singletonList(templateFoodListDTO.getTflFat().get(i)))
                        .tflGram(Collections.singletonList(templateFoodListDTO.getTflGram().get(i)))
                        .build();

                TemplateFoodList savedTemplateFoodList = templateFoodListRepository.save(templateFoodList);

                if (savedTemplateFoodList.getTflNum() != null) {
                    result = 1;
                } else {
                    result = -1;
                    break;
                }
            }
        } else {
            result = -1;
        }

        return result;
    }

    public int removeUserFoodTemplate(String uiId, Integer tfNum) {
        int result = 0;
        if (tfNum == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }

        TemplateFoodList templateFoodList = templateFoodListRepository.deleteByTfNum(tfNum);
        TemplateFood templateFood = templateFoodRepository.deleteByUiIdAndTfNum(uiId, tfNum);
        if(templateFoodList.getTfNum()==null){
            if(templateFood.getTfNum()==null){
                result = 1;
            }else {
                result = -1;
            }
        } else {
            result = -1;
        }
        return result;
    }


    public List<TemplateFoodProjection> listUserFoodTemplate(String uiId) {
        List<TemplateFoodProjection> templateFoodProjections = templateFoodRepository.findByUiId(uiId);
        return templateFoodProjections;
    }

    public TemplateFoodDetailDTO detailUserFoodTemplate(Integer tfNum) {
        // 1. TemplateFood 정보를 조회
        Optional<TemplateFood> templateOpt = templateFoodRepository.findByTfNum(tfNum);
        if (!templateOpt.isPresent()) {
            throw new RuntimeException("Template not found for ID: " + tfNum);
        }
        TemplateFood template = templateOpt.get();

        // 2. TemplateFoodList 정보를 조회
        List<TemplateFoodList> foodList = templateFoodListRepository.findByTfNum(tfNum);

        // 3. TemplateFood를 TemplateFoodDTO로 변환
        TemplateFoodDTO templateDTO = TemplateFoodDTO.builder()
                .tfNum(template.getTfNum())
                .uiId(template.getUiId())
                .tfName(template.getTfName())
                .build();

        // 4. TemplateFoodList를 TemplateFoodListDTO로 변환
        List<TemplateFoodListDTO> foodListDTOs = foodList.stream()
                .map(food -> TemplateFoodListDTO.builder()
                        .tflNum(food.getTflNum())
                        .tfNum(food.getTfNum())
                        .tflName(food.getTflName())
                        .tflKcal(food.getTflKcal())
                        .tflCarbs(food.getTflCarbs())
                        .tflProtein(food.getTflProtein())
                        .tflFat(food.getTflFat())
                        .tflGram(food.getTflGram())
                        .build())
                .collect(Collectors.toList());

        // 5. TemplateFoodDetailDTO 생성 및 설정
        TemplateFoodDetailDTO detailDTO = new TemplateFoodDetailDTO();
        detailDTO.setTemplate(templateDTO);
        detailDTO.setFoodList(foodListDTOs);

        return detailDTO;
    }
}
