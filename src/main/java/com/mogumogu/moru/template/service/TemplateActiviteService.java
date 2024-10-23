package com.mogumogu.moru.template.service;

import com.mogumogu.moru.template.dto.TemplateActiviteDTO;
import com.mogumogu.moru.template.dto.TemplateActiviteDetailDTO;
import com.mogumogu.moru.template.dto.TemplateActiviteListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TemplateActiviteService {

    int addUserActiviteTemplate(TemplateActiviteDTO templateActiviteDTO, TemplateActiviteListDTO templateActiviteListDTO);

    int removeUserActiviteTemplate(TemplateActiviteDTO templateActiviteDTO, TemplateActiviteListDTO templateActiviteListDTO);

    TemplateActiviteDetailDTO detailUserActiviteTemplate(Integer taNum);

    List<TemplateActiviteDTO> listUserActiviteTemplate(String uiId);
}
