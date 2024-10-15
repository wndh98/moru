package com.mogumogu.moru.diary.repository;

import com.mogumogu.moru.diary.entity.DiaryWalk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryWalkRepository extends JpaRepository<DiaryWalk,Integer> {
}
