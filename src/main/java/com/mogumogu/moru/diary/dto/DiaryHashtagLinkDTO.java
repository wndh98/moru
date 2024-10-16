package com.mogumogu.moru.diary.dto;

import com.mogumogu.moru.diary.entity.DiaryHashtag;
import com.mogumogu.moru.diary.entity.DiaryHashtagLink;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryHashtagLinkDTO {
    private Integer dhlNum;
    private DiaryHashtagDTO diaryHashtagDTO;
    private DiaryWalkDTO diaryWalkDTO;
    public static DiaryHashtagLinkDTO toDTO(DiaryHashtagLink diaryHashtagLink){
        return DiaryHashtagLinkDTO.builder()
                .dhlNum(diaryHashtagLink.getDhlNum())
                .diaryHashtagDTO(DiaryHashtagDTO.toDTO(diaryHashtagLink.getDiaryHashtag()))
//                .diaryWalkDTO(DiaryWalkDTO.toDTO(diaryHashtagLink.getDiaryWalk()))
                .build();
    }
}
