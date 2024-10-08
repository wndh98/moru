package com.mogumogu.moru.board.service;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.repository.BoardBaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardBaseRepository boardBaseRepository;


    /**
     * BoardBaseDTO 를 기준으로 게시판 INSERT
     * @param boardBaseDTO 게시판 dto
     * @return 1:성공 0:실패
     * @author 김주오
     */
    @Override
    @Transactional
    public int boardAdd(BoardBaseDTO boardBaseDTO) {
        // TODO : jwt 완성시 수정
        boardBaseDTO = BoardBaseDTO.builder().userInfoDTO(UserInfoDTO.builder().uiId("test").build()).boTitle("title").boContent("content").boType("free").boWriter("writer").build();
        int result = 0;
        BoardBase boardBase = boardBaseRepository.save(BoardBase.toEntity(boardBaseDTO));
        if (boardBase.getBoReply() == 0) {
            boardBase.setBoReply(boardBase.getBoNum());
        }

        if (boardBase.getBoNum() != null) {
            result = 1;
        }
        return result;
    }
}

