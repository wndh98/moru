package com.mogumogu.moru.board.service;

import com.mogumogu.moru.board.dto.BoardDTO;
import com.mogumogu.moru.board.repository.BoardBaseRepository;
import com.mogumogu.moru.board.repository.BoardFeedbackRepository;
import com.mogumogu.moru.board.repository.BoardFreeRepository;
import com.mogumogu.moru.board.repository.BoardNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardFreeRepository boardFreeRepository;
    @Autowired
    private BoardNoticeRepository boardNoticeRepository;
    @Autowired
    private BoardFeedbackRepository boardFeedbackRepository;


    @Override
    public BoardDTO getTestBoard(String boardType) {
        BoardBaseRepository<?, Integer> boardRepository= getBoardRepository(boardType);

        return null;
    }

    @Override
    public BoardBaseRepository<?,Integer> getBoardRepository(String boardType) {
        if (boardType.equals("free")) {
            return boardFreeRepository;
        } else if (boardType.equals("notice")) {
            return boardNoticeRepository;
        } else if (boardType.equals("feedback")){
            return boardFeedbackRepository;
        }
        return null;
    }
}
