package com.mogumogu.moru.pet.repository;

import com.mogumogu.moru.pet.entity.PetBreed;
import com.mogumogu.moru.pet.entity.PetWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetWeightRepository extends JpaRepository<PetWeight,Integer> {
}
