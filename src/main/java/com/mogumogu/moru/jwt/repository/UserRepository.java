package com.mogumogu.moru.jwt.repository;

import com.mogumogu.moru.jwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Boolean existsByUiId(String uiId);

    UserEntity findByUiId(String uiId);
}
