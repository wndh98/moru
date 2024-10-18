package com.mogumogu.moru.pet.repository;

import com.mogumogu.moru.pet.entity.PetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetInfoRepository extends JpaRepository<PetInfo,Integer> {
}