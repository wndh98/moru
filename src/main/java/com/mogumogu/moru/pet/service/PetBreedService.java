package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.pet.dto.PetBreedDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetBreedService {

    List<PetBreedDTO> PetBreedList(Pageable pageable);
}
