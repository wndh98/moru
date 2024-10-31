package com.mogumogu.moru.board.service;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.exception.BoardNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    int boardAdd(BoardBaseDTO boardBaseDTO);

    List<BoardBaseDTO> boardList(String boType, Pageable pageable, String searchType, String searchValue);

    BoardBaseDTO boardDetails(int boNum) throws BoardNotFoundException;

    int boardModify(BoardBaseDTO boardBaseDTO, int boNum) throws BoardNotFoundException;

    int boardRemove(int boNum) throws BoardNotFoundException;
}
