package com.mogumogu.moru.board.controller;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@Slf4j
public class BoardContoller {
    @Autowired
    BoardService boardService;

    @PostMapping("/board")
    public int addBoard(@RequestBody BoardBaseDTO boardBaseDTO){
        int result = 0;
        // TODO : boardBaseDTO의 userInfoDTO 에 user 정보 입력

        result = boardService.boardAdd(boardBaseDTO);
        return result;
    }
}
