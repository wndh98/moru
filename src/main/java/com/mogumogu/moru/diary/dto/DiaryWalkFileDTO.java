package com.mogumogu.moru.diary.dto;

import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.diary.entity.DiaryWalkFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryWalkFileDTO {
    private Integer dwfNum;
    private DiaryWalkDTO diaryWalkDTO;
    private UserInfoDTO userInfoDTO;
    private String dwfOldFilename;
    private String dwfRealFilename;
    private LocalDateTime dwfRegist;

    public static DiaryWalkFileDTO toDTO(DiaryWalkFile diaryWalkFile) {
        return DiaryWalkFileDTO.builder()
                .dwfNum(diaryWalkFile.getDwfNum())
                .diaryWalkDTO(DiaryWalkDTO.toDTO(diaryWalkFile.getDiaryWalk()))
                .userInfoDTO(UserInfoDTO.toDTO(diaryWalkFile.getUserInfo()))
                .dwfOldFilename(diaryWalkFile.getDwfOldFilename())
                .dwfRealFilename(diaryWalkFile.getDwfRealFilename())
                .dwfRegist(diaryWalkFile.getDwfRegist())
                .build();
    }
}
