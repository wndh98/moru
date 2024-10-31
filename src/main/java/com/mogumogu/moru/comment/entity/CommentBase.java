package com.mogumogu.moru.comment.entity;

import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.comment.dto.CommentBaseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENT_BASE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coNum;
    @ManyToOne
    @JoinColumn(name = "BO_NUM", nullable = false)
    private BoardBase boardBase;
    @ManyToOne
    @JoinColumn(name = "UI_ID", nullable = false)
    private UserInfo userInfo;
    private Integer coReply;
    @Builder.Default
    private Integer coReplyDept = 0;
    @Builder.Default
    private LocalDateTime coRegist = LocalDateTime.now();
    @Builder.Default
    private char coDel = 'N';
    private String coContent;

    public static CommentBase toEntity(CommentBaseDTO commentBaseDTO) {
        return CommentBase.builder()
                .coNum(commentBaseDTO.getCoNum())
                .boardBase(BoardBase.toEntity(commentBaseDTO.getBoardBaseDTO()))
                .userInfo(UserInfo.toEntity(commentBaseDTO.getUserInfoDTO()))
                .coReply(commentBaseDTO.getCoReply())
                .coReplyDept(commentBaseDTO.getCoReplyDept())
                .coRegist(commentBaseDTO.getCoRegist())
                .coDel(commentBaseDTO.getCoDel())
                .coContent(commentBaseDTO.getCoContent())
                .build();
    }

    @PrePersist
    public void prePersist() {
        if (coReply == null) {
            this.coReply = 0;
            this.coReplyDept = 0;
        }
    }
}
