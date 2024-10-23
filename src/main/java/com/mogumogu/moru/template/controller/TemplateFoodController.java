package com.mogumogu.moru.template.controller;

import com.mogumogu.moru.template.dto.TemplateFoodDTO;
import com.mogumogu.moru.template.dto.TemplateFoodDetailDTO;
import com.mogumogu.moru.template.dto.TemplateFoodListDTO;
import com.mogumogu.moru.template.projection.TemplateFoodProjection;
import com.mogumogu.moru.template.service.TemplateFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TemplateFoodController {
    @Autowired
    TemplateFoodService templateFoodService;

    @PostMapping("/userFoodTemplate")
    public int addUserFoodTemplate(@RequestBody TemplateFoodDTO templateFoodDTO,
                                   @RequestBody TemplateFoodListDTO templateFoodListDTO){
        int result = 0;
        result = templateFoodService.addUserFoodTemplate(templateFoodDTO,templateFoodListDTO);
        return result;
    }

    @DeleteMapping("/userFoodTemplate/{UI_ID}/{TF_NUM}")
    public int removeUserFoodTemplate(@PathVariable String uiId, @PathVariable Integer tfNum){
        int result = 0;
        result = templateFoodService.removeUserFoodTemplate(uiId,tfNum);
        return result;
    }

    @GetMapping("/userFoodTemplate/{UI_ID}")
    public List<TemplateFoodProjection> listUserFoodTemplate(@PathVariable String uiId){
        List<TemplateFoodProjection> templateFoodProjections = templateFoodService.listUserFoodTemplate(uiId);

        return templateFoodProjections;
    }

    @GetMapping("/userFoodTemplate/{TF_NUM}")
    public TemplateFoodDetailDTO detailUserFoodTemplate(@PathVariable Integer tfNum){
        return templateFoodService.detailUserFoodTemplate(tfNum);
    }
}
