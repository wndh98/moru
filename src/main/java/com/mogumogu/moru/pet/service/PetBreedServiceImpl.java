package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.pet.dto.PetBreedDTO;
import com.mogumogu.moru.pet.entity.PetBreed;
import com.mogumogu.moru.pet.repository.PetBreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetBreedServiceImpl implements PetBreedService {
    @Autowired
    private PetBreedRepository petBreedRepository;


    @Override
    public List<PetBreedDTO> PetBreedList(Pageable pageable) {
        Page<PetBreed> pageList;

        pageList = petBreedRepository.findAll(pageable);
        return pageList.stream().map(PetBreedDTO::toDTO).collect(Collectors.toList());
    }

}