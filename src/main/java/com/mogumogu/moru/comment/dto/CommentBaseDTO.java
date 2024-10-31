package com.mogumogu.moru.comment.dto;

import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.comment.entity.CommentBase;
import com.mogumogu.moru.board.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentBaseDTO {
    private Integer coNum;
    private BoardBase boardBase;
    private UserInfo userInfo;
    private Integer coReply;
    @Builder.Default
    private Integer coReplyDept = 0;
    @Builder.Default
    private LocalDateTime coRegist = LocalDateTime.now();
    @Builder.Default
    private char coDel = 'N';
    private String coContent;

    public static CommentBaseDTO toDTO(CommentBase commentBase) {
        return CommentBaseDTO.builder()
                .coNum(commentBase.getCoNum())
                .boardBase(commentBase.getBoardBase())
                .userInfo(commentBase.getUserInfo())
                .coReply(commentBase.getCoReply())
                .coReplyDept(commentBase.getCoReplyDept())
                .coRegist(commentBase.getCoRegist())
                .coDel(commentBase.getCoDel())
                .coContent(commentBase.getCoContent())
                .build();
    }
}
