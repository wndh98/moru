package com.mogumogu.moru.diary.dto;

import com.mogumogu.moru.diary.entity.DiaryHashtag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryHashtagDTO {
    private Integer dhNum;
    private String dhName;
    public static DiaryHashtagDTO toDTO(DiaryHashtag diaryHashtag){
        return DiaryHashtagDTO.builder()
                .dhNum(diaryHashtag.getDhNum())
                .dhName(diaryHashtag.getDhName())
                .build();
    }
}
