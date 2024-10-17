package com.mogumogu.moru.diary.dto;

import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.diary.entity.DiaryLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryLikeDTO {
    private Integer dlNum;
    private DiaryWalkDTO diaryWalkDTO;
    private UserInfoDTO userInfoDTO;
    public static DiaryLikeDTO toDTO(DiaryLike diaryLike){
        return DiaryLikeDTO.builder()
                .dlNum(diaryLike.getDlNum())
                .diaryWalkDTO(DiaryWalkDTO.toDTO(diaryLike.getDiaryWalk()))
                .userInfoDTO(UserInfoDTO.toDTO(diaryLike.getUserInfo()))
                .build();
    }
}
