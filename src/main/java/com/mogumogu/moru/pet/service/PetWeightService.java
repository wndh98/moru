package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.pet.dto.PetWeightDTO;
import com.mogumogu.moru.pet.exception.PetNotFoundException;

public interface PetWeightService {
    int weightSave(Integer piNum, PetWeightDTO petWeightDTO) throws PetNotFoundException;
}