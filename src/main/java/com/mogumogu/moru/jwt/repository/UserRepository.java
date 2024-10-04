package com.mogumogu.moru.jwt.repository;

import com.mogumogu.moru.jwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsById(String uiId);

    UserEntity findById(String uiId);
}
