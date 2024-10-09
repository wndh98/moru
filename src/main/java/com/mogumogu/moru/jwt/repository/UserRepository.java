package com.mogumogu.moru.jwt.repository;

import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserInfoEntity, String> {

    Boolean existsByUiId(String uiId);

    UserInfoEntity findByUiId(String uiId);
}
