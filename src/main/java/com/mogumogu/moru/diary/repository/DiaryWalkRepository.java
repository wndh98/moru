package com.mogumogu.moru.diary.repository;

import com.mogumogu.moru.diary.entity.DiaryWalk;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DiaryWalkRepository extends JpaRepository<DiaryWalk,Integer> {
    Page<DiaryWalk> findByDwDelAndDwPrivateAndUserInfoEntity(char dwDel, char dwPrivate, UserInfoEntity userInfoEntity, Pageable pageable);

    Page<DiaryWalk> findByDwDelAndDwPrivate(char dwDel,char dwPrivate, Pageable pageable);

    Page<DiaryWalk> findByDwDelAndUserInfoEntity(char dwDel, UserInfoEntity userInfoEntity, Pageable pageable);

    Optional<DiaryWalk> findByDwNumAndDwDel(Integer dwNum,char dwDel);
    Optional<DiaryWalk> findByDwNumAndDwDelAndDwPrivate(Integer dwNum,char dwDel,char dwPrivate);

    boolean existsByDwDelAndUserInfoEntityAndDwRegist(char dwDel, UserInfoEntity userInfoEntity, LocalDateTime dwRegist);
}
