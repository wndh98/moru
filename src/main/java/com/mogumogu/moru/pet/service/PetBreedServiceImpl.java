package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.pet.repository.PetBreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetBreedServiceImpl implements PetBreedService{
    @Autowired
    private PetBreedRepository petBreedRepository;

}
