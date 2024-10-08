package com.mogumogu.moru.board.service;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    int boardAdd(BoardBaseDTO boardBaseDTO);

    List<BoardBaseDTO> boardList(String boType, Pageable pageable, String searchType, String searchValue);
}
