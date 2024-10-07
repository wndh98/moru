package com.mogumogu.moru.jwt.repository;


import com.mogumogu.moru.jwt.entity.RefreshEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshRepository extends JpaRepository<RefreshEntity, Long> {

    //토큰이 존재하는지 확인
    Boolean existsByUrtToken(String urtToken);

    @Transactional
    void deleteByUrtToken(String urtToken);

}

