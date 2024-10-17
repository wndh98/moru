package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.pet.dto.PetInfoDTO;
import com.mogumogu.moru.pet.exception.PetNotFoundException;

public interface PetInfoService {
    int petInfoAdd(PetInfoDTO petInfoDTO);

    int petInfoRemove(Integer[] piNum) throws PetNotFoundException;
}
