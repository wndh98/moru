package com.mogumogu.moru.diary.dto;

import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryWalkDTO {
    private Integer dwNum;
    private UserInfoDTO userInfoDTO;
    private String dwTitle;
    private LocalDateTime dwRegist;
    private char dwPrivate;
    private Integer dwCount;
    private String dwContent;
    private char dwDel;

    public static DiaryWalkDTO toDTO(DiaryWalk diaryWalk){
        return DiaryWalkDTO.builder()
                .dwNum(diaryWalk.getDwNum())
                .userInfoDTO(UserInfoDTO.toDTO(diaryWalk.getUserInfo()))
                .dwTitle(diaryWalk.getDwTitle())
                .dwRegist(diaryWalk.getDwRegist())
                .dwPrivate(diaryWalk.getDwPrivate())
                .dwCount(diaryWalk.getDwCount())
                .dwContent(diaryWalk.getDwContent())
                .dwDel(diaryWalk.getDwDel())
                .build();
    }

}
