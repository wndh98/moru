package com.mogumogu.moru.board.controller;

import com.mogumogu.moru.board.dto.BoardDTO;
import com.mogumogu.moru.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Slf4j
public class BoardContoller {
    @Autowired
    BoardService boardService;

    @GetMapping("/boardtest")
    public BoardDTO getBoardTest(){
        boardService.getTestBoard("free");
        BoardDTO board = new BoardDTO();

        return board;
    }
}
