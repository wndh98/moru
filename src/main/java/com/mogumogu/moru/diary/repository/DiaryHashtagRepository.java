package com.mogumogu.moru.diary.repository;

import com.mogumogu.moru.diary.entity.DiaryHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryHashtagRepository extends JpaRepository<DiaryHashtag,Integer> {
    boolean existsByDhName(String dhName);

    Optional<DiaryHashtag> findByDhName(String dhName);
}
