package com.mogumogu.moru.diary.entity;

import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.diary.dto.DiaryWalkDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "DIARY_WALK_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryWalk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dwNum;
    @ManyToOne
    @JoinColumn(name="UI_ID",nullable=false)
    private UserInfo userInfo;
    private String dwTitle;
    @Builder.Default
    private LocalDateTime dwRegist= LocalDateTime.now();
    private char dwPrivate;
    @Builder.Default
    private Integer dwCount=0;
    private String dwContent;
    @Builder.Default
    private char dwDel='N';

    public static DiaryWalk toEntity(DiaryWalkDTO diaryWalkDTO){
        return DiaryWalk.builder()
                .dwNum(diaryWalkDTO.getDwNum())
                .userInfo(UserInfo.toEntity(diaryWalkDTO.getUserInfoDTO()))
                .dwTitle(diaryWalkDTO.getDwTitle())
                .dwRegist(diaryWalkDTO.getDwRegist())
                .dwPrivate(diaryWalkDTO.getDwPrivate())
                .dwCount(diaryWalkDTO.getDwCount())
                .dwContent(diaryWalkDTO.getDwContent())
                .dwDel(diaryWalkDTO.getDwDel())
                .build();
    }
    @PrePersist
    public void prePersist(){
        if(dwCount == null){
            this.dwCount = 0;
        }
        if(dwRegist==null){
            this.dwRegist=LocalDateTime.now();
        }
    }
}
