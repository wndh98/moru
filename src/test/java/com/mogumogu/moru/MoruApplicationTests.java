package com.mogumogu.moru;


import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.repository.BoardBaseRepository;
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

    @Test
    @Transactional
    public void boardAdd() {
        BoardBaseDTO boardBaseDTO = BoardBaseDTO.builder().userInfoDTO(UserInfoDTO.builder().uiId("test").build()).boTitle("title").boContent("content").boType("free").boWriter("writer").build();
        int result = 0;

        BoardBase boardBase = boardBaseRepository.save(BoardBase.toEntity(boardBaseDTO));
        boardBase.setBoReply(boardBase.getBoNum());
        boardBaseRepository.save(boardBase);
        System.out.println(boardBase);
        if(boardBase.getBoNum()!=null){
            result = 1;
        }
        System.out.println(result);
//        return result;
    }
}
