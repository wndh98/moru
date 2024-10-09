package com.mogumogu.moru.board.service;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.exception.BoardNotFoundException;
import com.mogumogu.moru.board.repository.BoardBaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    char BOARD_NOT_DEL = 'N';
    char BOARD_DEL = 'Y';
    @Autowired
    BoardBaseRepository boardBaseRepository;


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

    @Override
    public List<BoardBaseDTO> boardList(String boType, Pageable pageable, String searchType, String searchValue) {
        if (searchType == null) searchType = "";
        Page<BoardBase> pageList;

        if (searchType.equals("title")) {
            pageList = boardBaseRepository.findByBoDelAndBoTypeAndBoTitleContaining(BOARD_NOT_DEL, boType, searchValue, pageable);
        } else if (searchType.equals("content")) {
            pageList = boardBaseRepository.findByBoDelAndBoTypeAndBoContentContaining(BOARD_NOT_DEL, boType, searchValue, pageable);
        } else if (searchType.equals("both")) {
            pageList = boardBaseRepository.findByBoDelAndBoTypeAndBoTitleOrBoContentContaining(BOARD_NOT_DEL, boType, searchValue, searchValue, pageable);
        } else {
            pageList = boardBaseRepository.findByBoDelAndBoType(BOARD_NOT_DEL, boType, pageable);
        }

        return pageList.stream().map(BoardBaseDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public BoardBaseDTO boardDetails(int boNum) throws BoardNotFoundException {
        return BoardBaseDTO.toDTO(boardBaseRepository.findByBoNumAndBoDel(boNum,BOARD_NOT_DEL).orElseThrow(BoardNotFoundException::new));
    }

    @Override
    public int boardModify(BoardBaseDTO boardBaseDTO, int boNum) throws BoardNotFoundException {
        // TODO : 본인 체크
        int result = 1;
        // 게시판 번호 불일치
        if (boardBaseDTO.getBoNum() != boNum) {
            result = 0;
            return result;
        }
        BoardBase boardBase = boardBaseRepository.findByBoNumAndBoDel(boardBaseDTO.getBoNum(),BOARD_NOT_DEL).orElseThrow(BoardNotFoundException::new);
        boardBase.setBoContent(boardBaseDTO.getBoContent());
        boardBase.setBoTitle(boardBaseDTO.getBoTitle());
        boardBaseRepository.save(boardBase);
        return result;
    }

    @Override
    public int boardRemove(int boNum) throws BoardNotFoundException {
        // TODO : 본인 체크
        int result = 1;
        BoardBase boardBase = boardBaseRepository.findByBoNumAndBoDel(boNum,BOARD_NOT_DEL).orElseThrow(BoardNotFoundException::new);
        boardBase.setBoDel('Y');
        boardBaseRepository.save(boardBase);
        return result;
    }
}

