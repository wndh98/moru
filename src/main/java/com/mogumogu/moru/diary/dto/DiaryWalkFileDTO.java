package com.mogumogu.moru.diary.dto;

import com.mogumogu.moru.diary.entity.DiaryWalkFile;
import com.mogumogu.moru.jwt.dto.UserInfoDto;
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
    private UserInfoDto userInfoDto;
    private String dwfOldFilename;
    private String dwfRealFilename;
    private LocalDateTime dwfRegist;

    public static DiaryWalkFileDTO toDTO(DiaryWalkFile diaryWalkFile) {
        return DiaryWalkFileDTO.builder()
                .dwfNum(diaryWalkFile.getDwfNum())
                .diaryWalkDTO(DiaryWalkDTO.toDTO(diaryWalkFile.getDiaryWalk()))
                .userInfoDto(UserInfoDto.toDto(diaryWalkFile.getUserInfoEntity()))
                .dwfOldFilename(diaryWalkFile.getDwfOldFilename())
                .dwfRealFilename(diaryWalkFile.getDwfRealFilename())
                .dwfRegist(diaryWalkFile.getDwfRegist())
                .build();
    }
}
