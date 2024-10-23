package com.mogumogu.moru.template.controller;

import com.mogumogu.moru.template.dto.*;
import com.mogumogu.moru.template.service.TemplateActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TemplateActiviteController {
    @Autowired
    TemplateActiviteService templateActiviteService;

    @PostMapping("/userActiviteTemplate")
    public int addUserActiviteTemplate(@RequestBody TemplateActiviteDTO templateActiviteDTO,
                                       @RequestBody TemplateActiviteListDTO templateActiviteListDTO){
        int result = 0;
        result = templateActiviteService.addUserActiviteTemplate(templateActiviteDTO,templateActiviteListDTO);
        return result;
    }

    @DeleteMapping("/userActiviteTemplate/{UI_ID}/{TA_NUM}")
    public int removeUserActiviteTemplate(@RequestBody TemplateActiviteDTO templateActiviteDTO,
                                          @RequestBody TemplateActiviteListDTO templateActiviteListDTO){
        int result = 0;
        result = templateActiviteService.removeUserActiviteTemplate(templateActiviteDTO,templateActiviteListDTO);
        return result;
    }

    @GetMapping("/userActiviteTemplate/{TA_NUM}")
    public TemplateActiviteDetailDTO detailUserActiviteTemplate(@PathVariable Integer taNum) {

        return templateActiviteService.detailUserActiviteTemplate(taNum);
    }

    @GetMapping("/userActiviteTemplate/{UI_ID}")
    public List<TemplateActiviteDTO> listUserActiviteTemplate(@PathVariable String uiId){
        List<TemplateActiviteDTO> list = new ArrayList<>();
        list = templateActiviteService.listUserActiviteTemplate(uiId);
        return list;
    }
}
