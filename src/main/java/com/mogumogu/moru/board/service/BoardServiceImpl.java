package com.mogumogu.moru.board.service;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.BoardBase;
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
        if(searchType==null)searchType="";
        Page<BoardBase> pageList;

        if (searchType.equals("title")) {
            pageList = boardBaseRepository.findByBoTypeAndBoTitleContaining(boType, searchValue, pageable);
        } else if (searchType.equals("content")) {
            pageList = boardBaseRepository.findByBoTypeAndBoContentContaining(boType, searchValue, pageable);
        } else if (searchType.equals("both")) {
            pageList = boardBaseRepository.findByBoTypeAndBoTitleOrBoContentContaining(boType, searchValue, searchValue, pageable);
        } else {
            pageList = boardBaseRepository.findByBoType(boType, pageable);
        }

        return pageList.stream().map(BoardBaseDTO::toDTO).collect(Collectors.toList());
    }
}

