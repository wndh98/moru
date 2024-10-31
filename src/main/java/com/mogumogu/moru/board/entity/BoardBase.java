package com.mogumogu.moru.board.entity;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD_BASE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boNum;
    @ManyToOne
    @JoinColumn(name="UI_ID",nullable=false)
    private UserInfo userInfo;
    private String boTitle;
    private String boContent;
    private String boWriter;
    private Integer boReply;
    @Builder.Default
    private Integer boReplyDept=0;
    @Builder.Default
    private LocalDateTime boRegist = LocalDateTime.now();
    @Builder.Default
    private Integer boView = 0;
    @Builder.Default
    private char boDel = 'N';
    private String boType;

    public static BoardBase toEntity(BoardBaseDTO boardBaseDTO){
        return BoardBase.builder()
                .boNum(boardBaseDTO.getBoNum())
                .userInfo(UserInfo.toEntity(boardBaseDTO.getUserInfoDTO()))
                .boTitle(boardBaseDTO.getBoTitle())
                .boContent(boardBaseDTO.getBoContent())
                .boWriter(boardBaseDTO.getBoWriter())
                .boReply(boardBaseDTO.getBoReply())
                .boReplyDept(boardBaseDTO.getBoReplyDept())
                .boRegist(boardBaseDTO.getBoRegist())
                .boView(boardBaseDTO.getBoView())
                .boDel(boardBaseDTO.getBoDel())
                .boType(boardBaseDTO.getBoType())
                .build();
    }
    @PrePersist
    public void prePersist(){
        if(boReply == null){
            this.boReply = 0;
            this.boReplyDept = 0;
            this.boView = 0;
        }
    }
}
