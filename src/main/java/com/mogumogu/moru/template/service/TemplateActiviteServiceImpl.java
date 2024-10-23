package com.mogumogu.moru.template.service;

import com.mogumogu.moru.template.dto.TemplateActiviteDTO;
import com.mogumogu.moru.template.dto.TemplateActiviteDetailDTO;
import com.mogumogu.moru.template.dto.TemplateActiviteListDTO;
import com.mogumogu.moru.template.entity.TemplateActivite;
import com.mogumogu.moru.template.entity.TemplateActiviteList;
import com.mogumogu.moru.template.repository.TemplateActiviteListRepository;
import com.mogumogu.moru.template.repository.TemplateActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemplateActiviteServiceImpl implements TemplateActiviteService{
    @Autowired
    TemplateActiviteRepository templateActiviteRepository;

    @Autowired
    TemplateActiviteListRepository templateActiviteListRepository;


    public int addUserActiviteTemplate(TemplateActiviteDTO templateActiviteDTO, TemplateActiviteListDTO templateActiviteListDTO) {
        int result = 0;
        TemplateActivite templateActivite = TemplateActivite.toEntity(templateActiviteListDTO);
        TemplateActivite savedTemplate = templateActiviteRepository.save(templateActivite);

        if(savedTemplate.getTaNum()!=null){
            result=1;
        }else{
            result=-1;
        }
        if(templateActiviteListDTO.getUaNum()!=null&&templateActiviteListDTO.getTalTime()!=null
        &&templateActiviteListDTO.getUaNum().size()==templateActiviteListDTO.getTalTime().size()){
            for(int i = 0; i < templateActiviteListDTO.getUaNum().size(); i++){
                TemplateActiviteList templateActiviteList = TemplateActiviteList.toEntity(templateActiviteListDTO);
                TemplateActiviteList savedTemplateActiviteList = templateActiviteListRepository.save(templateActiviteList);
                if(savedTemplateActiviteList.getTalNum()!=null){
                    result = 1;
                }else{
                    result = -1;
                }
            }
        }
        return result;
    }

    public int removeUserActiviteTemplate(TemplateActiviteDTO templateActiviteDTO, TemplateActiviteListDTO templateActiviteListDTO) {
        int result = 0;

        TemplateActiviteList templateActiviteList =
                templateActiviteListRepository.deleteByTalNum(templateActiviteListDTO.getTalNum());

        TemplateActivite templateActivite = templateActiviteRepository.deleteByUiIdAndTaNum
                (templateActiviteDTO.getUiId(), templateActiviteDTO.getTaNum());

        if(templateActivite.getTaNum()==null){
            if(templateActiviteList.getTalNum()==null){
                result = 1;
            }else {
                result = -1;
            }
        }else{
            result = -1;
        }
        return result;
    }

    public TemplateActiviteDetailDTO detailUserActiviteTemplate(Integer taNum) {
        Optional<TemplateActivite> templateOpt = templateActiviteRepository.findByTaNum(taNum);
        if (!templateOpt.isPresent()) {
            throw new RuntimeException("Template not found for ID: " + taNum);
        }
        TemplateActivite template = templateOpt.get();

        List<TemplateActiviteList> activiteList = templateActiviteListRepository.findByTaNum(taNum);

        TemplateActiviteDTO templateDTO = TemplateActiviteDTO.toDTO(template);

        List<TemplateActiviteListDTO> activiteListDTOs = activiteList.stream()
                .map(list -> TemplateActiviteListDTO.toDTO(list))
                .collect(Collectors.toList());

        TemplateActiviteDetailDTO detailDTO = new TemplateActiviteDetailDTO();
        detailDTO.setTemplate(templateDTO);
        detailDTO.setActiviteList(activiteListDTOs);

        return detailDTO;
    }

    public List<TemplateActiviteDTO> listUserActiviteTemplate(String uiId) {
        List<TemplateActivite> list = templateActiviteRepository.findByUiId(uiId);

        return list.stream()
                .map(TemplateActiviteDTO::toDTO)
                .collect(Collectors.toList());
    }
}
