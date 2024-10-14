package com.mogumogu.moru.board.entity;


import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.dto.BoardFileDTO;
import com.mogumogu.moru.board.dto.UserInfoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD_FILE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bfNum;
    @ManyToOne
    @JoinColumn(name="BO_NUM",nullable=false)
    private BoardBase boardBase;
    @ManyToOne
    @JoinColumn(name="UI_ID",nullable=false)
    private UserInfo userInfo;
    private String bfOldFilename;
    private String bfRealFilename;
    @Builder.Default
    private LocalDateTime bfRegist = LocalDateTime.now();
    private String bfType;

    public static BoardFile toEntity(BoardFileDTO boardFileDTO){
        return BoardFile.builder()
                .bfNum(boardFileDTO.getBfNum())
                .boardBase(BoardBase.toEntity(boardFileDTO.getBoardBaseDTO()))
                .userInfo(UserInfo.toEntity(boardFileDTO.getUserInfoDTO()))
                .bfOldFilename(boardFileDTO.getBfOldFilename())
                .bfRealFilename(boardFileDTO.getBfRealFilename())
                .bfRegist(boardFileDTO.getBfRegist())
                .bfType(boardFileDTO.getBfType())
                .build();
    }
}
