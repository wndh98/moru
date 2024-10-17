package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.pet.repository.PetInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetInfoServiceImpl implements PetInfoService{
    @Autowired
    private PetInfoRepository petInfoRepository;
}
