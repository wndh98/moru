package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.pet.dto.PetInfoDTO;
import com.mogumogu.moru.pet.dto.PetWeightDTO;
import com.mogumogu.moru.pet.entity.PetInfo;
import com.mogumogu.moru.pet.entity.PetWeight;
import com.mogumogu.moru.pet.repository.PetWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetWeightServiceImpl implements PetWeightService {
    @Autowired
    private PetWeightRepository petWeightRepository;

    @Override
    public int weightSave(Integer piNum, PetWeightDTO petWeightDTO) {
        int result = 0;
        PetInfoDTO petInfoDTO = PetInfoDTO.builder().piNum(piNum).build();
        PetInfo petInfo = PetInfo.toEntity(petInfoDTO);
        PetWeight petWeight = petWeightRepository.findByPetInfoAndPwDate(petInfo, petWeightDTO.getPwDate()).orElse(null);
        if (petWeight == null) {
            petWeightDTO.setPetInfoDTO(petInfoDTO);
            petWeightRepository.save(PetWeight.toEntity(petWeightDTO));
            result = 1;
        } else {
            petWeight.setPwWeight(petWeight.getPwWeight());
            petWeightRepository.save(petWeight);
            result = 1;
        }
        return result;
    }
}
