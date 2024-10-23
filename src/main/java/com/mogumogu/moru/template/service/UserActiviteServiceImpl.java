package com.mogumogu.moru.template.service;

import com.mogumogu.moru.template.dto.UserActiviteDTO;
import com.mogumogu.moru.template.entity.UserActivite;
import com.mogumogu.moru.template.repository.UserActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserActiviteServiceImpl implements UserActiviteService{

    @Autowired
    UserActiviteRepository userActiviteRepository;

    public int addUserActivite(UserActiviteDTO userActiviteDTO) {
        int result = 0;
        UserActivite userActivite = UserActivite.toEntity(userActiviteDTO);

        UserActivite savedUserActivite = userActiviteRepository.save(userActivite);

        if(savedUserActivite.getUaNum()!=null){
            result = 1;
        }
        return result;
    }

    public List<UserActiviteDTO> listUserActivite() {
        List<UserActivite> userActivite = userActiviteRepository.findAll();

        return userActivite.stream()
                .map(UserActiviteDTO::toDTO)
                .collect(Collectors.toList());
    }
}
