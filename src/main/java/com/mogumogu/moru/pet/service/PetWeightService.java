package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.pet.dto.PetWeightDTO;

public interface PetWeightService {
    int weightSave(Integer piNum, PetWeightDTO petWeightDTO);
}
