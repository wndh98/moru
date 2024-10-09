package com.mogumogu.moru.jwt.repository;

import com.mogumogu.moru.jwt.entity.JWTUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<JWTUserEntity, String> {

    Boolean existsByUiId(String uiId);

    JWTUserEntity findByUiId(String uiId);
}
