package com.mogumogu.moru.jwt.repository;


import com.mogumogu.moru.jwt.entity.RefreshEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshEntity, String> {

    //토큰이 존재하는지 확인
    Boolean existsByUrtToken(String urtToken);

    Boolean existsByUiId(String uiId);

    @Transactional
    void deleteByUrtToken(String urtToken);

    @Transactional
    void deleteByUiId(String uiId);

}

