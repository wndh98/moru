package com.mogumogu.moru.board.dto;

import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.entity.BoardFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardFileDTO {
    private Integer bfNum;
    private BoardBaseDTO boardBaseDTO;
    private UserInfoDTO userInfoDTO;
    private String bfOldFilename;
    private String bfRealFilename;
    @Builder.Default
    private LocalDateTime bfRegist = LocalDateTime.now();
    private String bfType;

    public static BoardFileDTO toDTO(BoardFile boardFile){
        return BoardFileDTO.builder()
                .bfNum(boardFile.getBfNum())
                .boardBaseDTO(BoardBaseDTO.toDTO(boardFile.getBoardBase()))
                .userInfoDTO(UserInfoDTO.toDTO(boardFile.getUserInfo()))
                .bfOldFilename(boardFile.getBfOldFilename())
                .bfRealFilename(boardFile.getBfRealFilename())
                .bfRegist(boardFile.getBfRegist())
                .bfType(boardFile.getBfType())
                .build();
    }
}
