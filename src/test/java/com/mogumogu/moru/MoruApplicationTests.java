package com.mogumogu.moru;


import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.exception.BoardNotFoundException;
import com.mogumogu.moru.board.repository.BoardBaseRepository;
import com.mogumogu.moru.comment.dto.CommentBaseDTO;
import com.mogumogu.moru.comment.entity.CommentBase;
import com.mogumogu.moru.comment.repository.CommentBaseRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MoruApplicationTests {
    @Autowired
    BoardBaseRepository boardBaseRepository;
    @Autowired
    CommentBaseRepository commentBaseRepository;

    @Test
    public void testcode()  {
        // TODO : jwt 완성시 수정
        CommentBaseDTO commentBaseDTO = CommentBaseDTO.builder().boardBaseDTO(BoardBaseDTO.builder().userInfoDTO(UserInfoDTO.builder().uiId("test").build()).boNum(1).build()).userInfoDTO(UserInfoDTO.builder().uiId("test").build()).coContent("commentcontent").build();
        int result = 0;
        CommentBase commentBase = commentBaseRepository.save(CommentBase.toEntity(commentBaseDTO));
        if (commentBase.getCoReply() == 0) {
            commentBase.setCoReply(commentBase.getCoNum());
        }
        if (commentBase.getCoNum() != null) {
            result = 1;
        }
        System.out.println(result);
    }
}
