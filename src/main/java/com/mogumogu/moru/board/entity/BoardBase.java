package com.mogumogu.moru.board.entity;

import com.mogumogu.moru.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boNum;
    @ManyToOne
    @JoinColumn(name="UI_ID",referencedColumnName = "UI_ID",nullable=false)
    private UserInfo userInfo;
    private String boTitle;
    private String boContent;
    private String boWriter;
    private Integer boReply;
    private Integer boReplyDept;
    private LocalDateTime boRegist;
    private Integer boView = 0;
    private String boDel = "N";

//    public static BoardBase toEntity(BoardDTO boardDTO){
//        BoardBase.builder()
//                .boNum(boardDTO.getBoNum())
//                .userInfo(boardDTO.getUserInfoDTO())
//    }



}
