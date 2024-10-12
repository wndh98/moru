package com.mogumogu.moru;


import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.exception.BoardNotFoundException;
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
    public void boardModify() throws BoardNotFoundException {
        int boNum = 10;
        int result = 1;
        BoardBase boardBase = boardBaseRepository.findById(boNum).orElseThrow(BoardNotFoundException::new);
        boardBase.setBoDel('Y');;
        boardBaseRepository.save(boardBase);
    }
}
