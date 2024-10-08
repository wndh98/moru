package com.mogumogu.moru.board.controller;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Slf4j
public class BoardContoller {
    @Autowired
    BoardService boardService;

    /**
     * BoardBaseDTO 를 기준으로 게시판 INSERT
     * @param boardBaseDTO 게시판 dto
     * @return 1:성공 0:실패
     * @author 김주오
     */
    @PostMapping("/board")
    public int addBoard(@RequestBody BoardBaseDTO boardBaseDTO) {
        int result = 0;
        // TODO : boardBaseDTO의 userInfoDTO 에 user 정보 입력

        result = boardService.boardAdd(boardBaseDTO);
        return result;
    }


    /**
     * BoardBaseDTO 를 기준으로 게시판 LIST
     * @param boType 게시판 타입
     * @param searchType 검색 타입
     * @param searchValue 검색어
     * @param pageable 페이징 (page:현재페이지,size:페이지 갯수)
     * @return List<BoardBaseDTO>
     * @author 김주오
     */
    @GetMapping("/board/{boType}")
    public List<BoardBaseDTO> listBoard(
            @PathVariable("boType") String boType,
            @RequestParam(name = "searchType", required = false) String searchType,
            @RequestParam(name = "searchValue", required = false) String searchValue,
            Pageable pageable
    ) {
        List<BoardBaseDTO> list = new ArrayList<>();
        list = boardService.boardList(boType, pageable, searchType, searchValue);
        return list;
    }
}
