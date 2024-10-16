package com.mogumogu.moru.diary.entity;

import com.mogumogu.moru.diary.dto.DiaryHashtagDTO;
import com.mogumogu.moru.diary.dto.DiaryHashtagLinkDTO;
import com.mogumogu.moru.diary.dto.DiaryWalkDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "DIARY_HASHTAG_LINK_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryHashtagLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dhlNum;
    @ManyToOne
    @JoinColumn(name="DH_NUM",nullable=false)
    private DiaryHashtag diaryHashtag;
    @ManyToOne
    @JoinColumn(name="DW_NUM")
    private DiaryWalk diaryWalk;
    public static DiaryHashtagLink toEntity(DiaryHashtagLinkDTO diaryHashtagLinkDTO){
        return DiaryHashtagLink.builder()
                .dhlNum(diaryHashtagLinkDTO.getDhlNum())
                .diaryHashtag(DiaryHashtag.toEntity(diaryHashtagLinkDTO.getDiaryHashtagDTO()))
                .diaryWalk(DiaryWalk.toEntity(diaryHashtagLinkDTO.getDiaryWalkDTO()))
                .build();
    }
}
