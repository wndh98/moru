package com.mogumogu.moru.user.repository;

import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, String> {

    UserInfoEntity findByUiId(String uiId);
}

