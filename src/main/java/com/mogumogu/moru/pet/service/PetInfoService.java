package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.pet.dto.PetInfoDTO;
import com.mogumogu.moru.pet.exception.PetNotFoundException;

import java.util.List;

public interface PetInfoService {
    int petInfoAdd(PetInfoDTO petInfoDTO);

    int petInfoRemove(List<Integer> piNums) throws PetNotFoundException;
}
