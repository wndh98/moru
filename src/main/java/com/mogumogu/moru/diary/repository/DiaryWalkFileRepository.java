package com.mogumogu.moru.diary.repository;

import com.mogumogu.moru.diary.entity.DiaryWalkFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryWalkFileRepository extends JpaRepository<DiaryWalkFile,Integer> {
}
