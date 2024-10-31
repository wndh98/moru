package com.mogumogu.moru.diary.dto;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.diary.entity.DiaryHashtagLink;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private String[] dhNames;
    private List<DiaryHashtagLinkDTO> diaryHashtagLinksDTO;


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
                .diaryHashtagLinksDTO(diaryWalk.getDiaryHashtagLinks().stream().map(DiaryHashtagLinkDTO::toDTO).collect(Collectors.toList()))
                .build();
    }

}
