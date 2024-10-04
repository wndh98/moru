package com.mogumogu.moru.board.service;

import com.mogumogu.moru.board.dto.BoardDTO;
import com.mogumogu.moru.board.repository.BoardBaseRepository;

public interface BoardService {
    BoardDTO getTestBoard(String boardType);


    BoardBaseRepository<?,Integer> getBoardRepository(String boardType);
}
