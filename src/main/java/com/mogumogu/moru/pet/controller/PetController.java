package com.mogumogu.moru.pet.controller;

import com.mogumogu.moru.pet.dto.PetInfoDTO;
import com.mogumogu.moru.pet.dto.PetWeightDTO;
import com.mogumogu.moru.pet.exception.PetNotFoundException;
import com.mogumogu.moru.pet.service.PetBreedService;
import com.mogumogu.moru.pet.service.PetInfoService;
import com.mogumogu.moru.pet.service.PetWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PetController {
    @Autowired
    private PetBreedService petBreedService;
    @Autowired
    private PetInfoService petInfoService;
    @Autowired
    private PetWeightService petWeightService;


    @PostMapping("/pets")
    public int addPetInfo(@RequestBody PetInfoDTO petInfoDTO){
        int result = 0;
        result = petInfoService.petInfoAdd(petInfoDTO);
        return result;
    }

    @DeleteMapping("/pets")
    public int removePetInfo(@RequestBody Integer[] piNum){
        int result = 0;
        try {
            result = petInfoService.petInfoRemove(piNum);
        } catch (PetNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @PostMapping("/petsWeight/{piNum}")
    public int saveWeight(@PathVariable("piNum")Integer piNum, PetWeightDTO petWeightDTO){
        int result = 0;
        result = petWeightService.weightSave(piNum,petWeightDTO);
        return result;

    }

}
