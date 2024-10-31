package com.mogumogu.moru.user.repository;

import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, String> {

    Optional<UserInfoEntity> findByUiId(String uiId);

    Optional<UserInfoEntity> findByUiNickname(String uiNickname);
}

