package com.mogumogu.moru.diary.repository;

import com.mogumogu.moru.diary.entity.DiaryHashtag;
import com.mogumogu.moru.diary.entity.DiaryHashtagLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryHashtagLinkRepository extends JpaRepository<DiaryHashtagLink,Integer> {
}
