package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.pet.repository.PetWeightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetWeightServiceImpl implements PetWeightService{
    @Autowired
    private PetWeightRepository petWeightRepository;
}
