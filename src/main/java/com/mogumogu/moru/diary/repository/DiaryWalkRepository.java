package com.mogumogu.moru.diary.repository;

import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaryWalkRepository extends JpaRepository<DiaryWalk,Integer> {
    Page<DiaryWalk> findByDwDelAndDwPrivateAndUiId(char dwDel,char dwPrivate, String uiId, Pageable pageable);

    Page<DiaryWalk> findByDwDelAndDwPrivate(char dwDel,char dwPrivate, Pageable pageable);

    Page<DiaryWalk> findByDwDelAndUiId(char dwDel, String uiId, Pageable pageable);

    Optional<DiaryWalk> findByDwNumAndDwDel(Integer dwNum,char dwDel);
    Optional<DiaryWalk> findByDwNumAndDwDelAndDwPrivate(Integer dwNum,char dwDel,char dwPrivate);
}
