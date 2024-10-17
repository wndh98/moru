package com.mogumogu.moru.diary.entity;

import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.diary.dto.DiaryLikeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIARY_LIKE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dlNum;
    @ManyToOne
    @JoinColumn(name="DW_NUM",nullable=false)
    private DiaryWalk diaryWalk;
    @ManyToOne
    @JoinColumn(name="UI_ID",nullable=false)
    private UserInfo userInfo;

    public static DiaryLike toEntity(DiaryLikeDTO diaryLikeDTO){
        return DiaryLike.builder()
                .dlNum(diaryLikeDTO.getDlNum())
                .diaryWalk(DiaryWalk.toEntity(diaryLikeDTO.getDiaryWalkDTO()))
                .userInfo(UserInfo.toEntity(diaryLikeDTO.getUserInfoDTO()))
                .build();
    }
}
