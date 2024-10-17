package com.mogumogu.moru.pet.controller;

import com.mogumogu.moru.pet.service.PetBreedService;
import com.mogumogu.moru.pet.service.PetInfoService;
import com.mogumogu.moru.pet.service.PetWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
    @Autowired
    private PetBreedService petBreedService;
    @Autowired
    private PetInfoService petInfoService;
    @Autowired
    private PetWeightService petWeightService;
}
