package com.mogumogu.moru.pet.repository;

import com.mogumogu.moru.pet.dto.PetInfoDTO;
import com.mogumogu.moru.pet.entity.PetBreed;
import com.mogumogu.moru.pet.entity.PetInfo;
import com.mogumogu.moru.pet.entity.PetWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PetWeightRepository extends JpaRepository<PetWeight,Integer> {
    Optional<PetWeight> findByPetInfoAndPwDate(PetInfo petInfo, LocalDateTime pwDate);
}
