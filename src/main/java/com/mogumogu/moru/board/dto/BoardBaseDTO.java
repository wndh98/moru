package com.mogumogu.moru.board.dto;

import com.mogumogu.moru.board.entity.BoardBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardBaseDTO {
    private Integer boNum;
    private UserInfoDTO userInfoDTO;
    private String boTitle;
    private String boContent;
    private String boWriter;
    private Integer boReply;
    private Integer boReplyDept;
    @Builder.Default
    private LocalDateTime boRegist = LocalDateTime.now();
    private Integer boView;
    @Builder.Default
    private char boDel='N';
    private String boType;

    public static BoardBaseDTO toDTO(BoardBase boardBase){
        return BoardBaseDTO.builder()
                .boNum(boardBase.getBoNum())
                .userInfoDTO(UserInfoDTO.toDTO(boardBase.getUserInfo()))
                .boTitle(boardBase.getBoTitle())
                .boContent(boardBase.getBoContent())
                .boWriter(boardBase.getBoWriter())
                .boReply(boardBase.getBoReply())
                .boReplyDept(boardBase.getBoReplyDept())
                .boRegist(boardBase.getBoRegist())
                .boView(boardBase.getBoView())
                .boDel(boardBase.getBoDel())
                .boType(boardBase.getBoType())
                .build();
    }
}
