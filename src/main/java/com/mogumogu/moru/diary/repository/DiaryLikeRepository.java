package com.mogumogu.moru.diary.repository;

import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.diary.entity.DiaryLike;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaryLikeRepository extends JpaRepository<DiaryLike, Integer> {
    Optional<DiaryLike> findByUserInfoAndDiaryWalk(UserInfo userInfo, DiaryWalk diaryWalk);
}
