package com.mogumogu.moru.board.controller;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.dto.BoardFileDTO;
import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.entity.BoardFile;
import com.mogumogu.moru.board.exception.BoardNotFoundException;
import com.mogumogu.moru.board.service.BoardFileService;
import com.mogumogu.moru.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
//@Slf4j
public class BoardContoller {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardFileService boardFileService;

    /**
     * BoardBaseDTO 를 기준으로 게시판 INSERT
     *
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
     *
     * @param boType      게시판 타입
     * @param searchType  검색 타입
     * @param searchValue 검색어
     * @param pageable    페이징 (page:현재페이지,size:페이지 갯수)
     * @return List<BoardBaseDTO>
     * @author 김주오
     */
    @GetMapping("/board/list/{boType}")
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

    /**
     * BoardBaseDTO 를 기준으로 게시판 LIST
     *
     * @param boNum 게시판 번호
     * @return BoardBaseDTO
     * @throws BoardNotFoundException
     * @author 김주오
     */
    @GetMapping("/board/{boNum}")
    public BoardBaseDTO detailsBoard(@PathVariable("boNum") int boNum) {
        BoardBaseDTO boardBaseDTO = null;
        try {
            boardBaseDTO = boardService.boardDetails(boNum);
        } catch (BoardNotFoundException e) {
            throw new RuntimeException(e);
        }
        return boardBaseDTO;
    }

    /**
     * BoardBaseDTO 를 기준으로 게시판 수정
     * boNum 으로 게시판 번호 2중체크
     *
     * @param boNum        게시판 번호
     * @param boardBaseDTO 수정 내용 boardBaseDTO
     * @return 1 : 성공 0 실패
     * @author 김주오
     */
    @PutMapping("/board/{boNum}")
    public int modifyBoard(@RequestBody BoardBaseDTO boardBaseDTO, @PathVariable("boNum") int boNum) {
        int result = 0;
        try {
            result = boardService.boardModify(boardBaseDTO, boNum);
        } catch (BoardNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * BoardBaseDTO 를 기준으로 게시판 소프트 DELETE
     * boNum 으로 게시판 번호 2중체크
     *
     * @param boNum 게시판 번호
     * @return 1 : 성공 0 실패
     * @author 김주오
     */
    @DeleteMapping("/board/{boNum}")
    public int removeBoard(@PathVariable("boNum") int boNum) {
        int result = 0;
        try {
            result = boardService.boardRemove(boNum);
        } catch (BoardNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @PostMapping("/boardFile/{boType}/{boNum}")
    public List<String> addBoardFile(@PathVariable("boType") String boType, @PathVariable("boNum") Integer boNum, @RequestPart("bfFile") MultipartFile[] bfFiles) {
        List<String> list = null;
        try {
            list = boardFileService.BoardFileadd(boType, boNum, bfFiles);
        } catch (BoardNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
