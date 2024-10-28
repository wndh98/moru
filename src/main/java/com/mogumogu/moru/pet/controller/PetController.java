package com.mogumogu.moru.pet.controller;

import com.mogumogu.moru.pet.dto.PetBreedDTO;
import com.mogumogu.moru.pet.dto.PetInfoDTO;
import com.mogumogu.moru.pet.dto.PetWeightDTO;
import com.mogumogu.moru.pet.exception.PetNotFoundException;
import com.mogumogu.moru.pet.service.PetBreedService;
import com.mogumogu.moru.pet.service.PetInfoService;
import com.mogumogu.moru.pet.service.PetWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    private PetBreedService petBreedService;
    @Autowired
    private PetInfoService petInfoService;
    @Autowired
    private PetWeightService petWeightService;


    @PostMapping("/pets")
    public int addPetInfo(@RequestBody PetInfoDTO petInfoDTO) {
        int result = 0;
        result = petInfoService.petInfoAdd(petInfoDTO);
        return result;
    }

    @PutMapping("pets/{piNum}")
    public int modifyPetInfo(@PathVariable("piNum") Integer piNum, @RequestBody PetInfoDTO petInfoDTO) {
        int result = 0;
        try {
            result = petInfoService.petInfoModify(piNum, petInfoDTO);
        } catch (PetNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @DeleteMapping("/pets")
    public int removePetInfo(@RequestBody List<Integer> piNums) {
        int result = 0;
        try {
            result = petInfoService.petInfoRemove(piNums);
        } catch (PetNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @PostMapping("/petsWeight/{piNum}")
    public int saveWeight(@PathVariable("piNum") Integer piNum, @RequestBody PetWeightDTO petWeightDTO) {
        int result = 0;
        try {
            result = petWeightService.weightSave(piNum, petWeightDTO);
        } catch (PetNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @DeleteMapping("/petsWeight")
    public int removeWeight(@RequestBody List<Integer> pwNums) {
        int result = 0;
        try {
            result = petWeightService.petRemoveWeight(pwNums);
        } catch (PetNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @GetMapping("/petsBreed")
    public List<PetBreedDTO> listPetBreed(Pageable pageable) {
        List<PetBreedDTO> list = null;
        list = petBreedService.PetBreedList(pageable);
        return list;
    }

}
