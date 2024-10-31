package com.mogumogu.moru.diary.entity;

import com.mogumogu.moru.diary.dto.DiaryWalkFileDTO;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "DIARY_WALK_FILE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryWalkFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dwfNum;
    @ManyToOne
    @JoinColumn(name = "DW_NUM", nullable = false)
    private DiaryWalk diaryWalk;
    @ManyToOne
    @JoinColumn(name = "UI_ID", nullable = false)
    private UserInfoEntity userInfoEntity;
    private String dwfOldFilename;
    private String dwfRealFilename;
    @Builder.Default
    private LocalDateTime dwfRegist = LocalDateTime.now();

    public static DiaryWalkFile toDTO(DiaryWalkFileDTO diaryWalkFileDTO) {
        return DiaryWalkFile.builder()
                .dwfNum(diaryWalkFileDTO.getDwfNum())
                .diaryWalk(DiaryWalk.toEntity(diaryWalkFileDTO.getDiaryWalkDTO()))
                .userInfoEntity(UserInfoEntity.toEntity(diaryWalkFileDTO.getUserInfoDto()))
                .dwfOldFilename(diaryWalkFileDTO.getDwfOldFilename())
                .dwfRealFilename(diaryWalkFileDTO.getDwfRealFilename())
                .dwfRegist(diaryWalkFileDTO.getDwfRegist())
                .build();
    }
    @PrePersist
    public void prePersist(){
        if(dwfRegist==null){
            this.dwfRegist=LocalDateTime.now();
        }
    }
}
