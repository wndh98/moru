package com.mogumogu.moru.template.service;

import com.mogumogu.moru.template.dto.UserActiviteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserActiviteService {
    int addUserActivite(UserActiviteDTO userActiviteDTO);

    List<UserActiviteDTO> listUserActivite();
}
