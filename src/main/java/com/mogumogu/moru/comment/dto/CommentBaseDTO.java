package com.mogumogu.moru.comment.dto;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.comment.entity.CommentBase;
import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
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
    private BoardBaseDTO boardBaseDTO;
    private UserInfoDto userInfoDto;
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
                .boardBaseDTO(commentBase.getBoardBase())
                .userInfoDto(UserInfoDto.toDto(commentBase.getUserInfoEntity()))
                .coReply(commentBase.getCoReply())
                .coReplyDept(commentBase.getCoReplyDept())
                .coRegist(commentBase.getCoRegist())
                .coDel(commentBase.getCoDel())
                .coContent(commentBase.getCoContent())
                .build();
    }
}
