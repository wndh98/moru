package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.pet.dto.PetInfoDTO;
import com.mogumogu.moru.pet.dto.PetWeightDTO;
import com.mogumogu.moru.pet.entity.PetInfo;
import com.mogumogu.moru.pet.entity.PetWeight;
import com.mogumogu.moru.pet.exception.PetNotFoundException;
import com.mogumogu.moru.pet.repository.PetInfoRepository;
import com.mogumogu.moru.pet.repository.PetWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetWeightServiceImpl implements PetWeightService {
    @Autowired
    private PetWeightRepository petWeightRepository;
    @Autowired
    private PetInfoRepository petInfoRepository;
    @Override
    public int weightSave(Integer piNum, PetWeightDTO petWeightDTO) throws PetNotFoundException {
        int result = 0;
        PetInfo petInfo = petInfoRepository.findById(piNum).orElseThrow(PetNotFoundException::new);
        PetWeight petWeight = petWeightRepository.findByPetInfoAndPwDate(petInfo, petWeightDTO.getPwDate()).orElse(null);
        if (petWeight == null) {
            petWeightDTO.setPetInfoDTO(PetInfoDTO.toDTO(petInfo));
            petWeightRepository.save(PetWeight.toEntity(petWeightDTO));
        } else {
            petWeight.setPwWeight(petWeight.getPwWeight());
            petWeightRepository.save(petWeight);
        }
        result = 1;
        return result;
    }
}
