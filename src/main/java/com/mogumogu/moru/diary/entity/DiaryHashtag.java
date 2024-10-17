package com.mogumogu.moru.diary.entity;

import com.mogumogu.moru.diary.dto.DiaryHashtagDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DIARY_HASHTAG_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dhNum;
    private String dhName;

    public static DiaryHashtag toEntity(DiaryHashtagDTO diaryHashtagDTO){
        return DiaryHashtag.builder()
                .dhNum(diaryHashtagDTO.getDhNum())
                .dhName(diaryHashtagDTO.getDhName())
                .build();
    }
}
