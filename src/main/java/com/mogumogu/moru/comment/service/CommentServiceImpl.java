package com.mogumogu.moru.comment.service;

import com.mogumogu.moru.board.dto.BoardBaseDTO;
import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.exception.BoardNotFoundException;
import com.mogumogu.moru.comment.dto.CommentBaseDTO;
import com.mogumogu.moru.comment.entity.CommentBase;
import com.mogumogu.moru.comment.exception.CommentNotFoundException;
import com.mogumogu.moru.comment.repository.CommentBaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final char COMMENT_NOT_DEL = 'N';
    private final char COMMENT_DEL = 'Y';

    @Autowired
    private CommentBaseRepository commentBaseRepository;

    @Override
    public List<CommentBaseDTO> commentList(Integer boNum, Pageable pageable) {
        Page<CommentBase> pageList;

        pageList = commentBaseRepository.findByCoDelAndBoardBase(COMMENT_NOT_DEL, BoardBase.builder().boNum(boNum).build(), pageable);
        return pageList.stream().map(CommentBaseDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int commentAdd(CommentBaseDTO commentBaseDTO) {
        // TODO : jwt 완성시 수정
        commentBaseDTO = CommentBaseDTO.builder().boardBaseDTO(BoardBaseDTO.builder().userInfoDTO(UserInfoDTO.builder().uiId("test").build()).boNum(1).build()).userInfoDTO(UserInfoDTO.builder().uiId("test").build()).coContent("내용").build();
        int result = 0;
        CommentBase commentBase = commentBaseRepository.save(CommentBase.toEntity(commentBaseDTO));
        if (commentBase.getCoReply() == 0) {
            commentBase.setCoReply(commentBase.getCoNum());
        }
        if (commentBase.getCoNum() != null) {
            result = 1;
        }
        return result;
    }

    @Override
    public int commentModify(Integer coNum, CommentBaseDTO commentBaseDTO) throws CommentNotFoundException {
        // TODO : 본인 체크
        int result = 1;
        // 게시판 번호 불일치
        if (!Objects.equals(commentBaseDTO.getCoNum(), coNum)) {
            result = 0;
            return result;
        }
        CommentBase commentBase = commentBaseRepository.findByCoNumAndCoDel(commentBaseDTO.getCoNum(), COMMENT_NOT_DEL).orElseThrow(CommentNotFoundException::new);
        commentBase.setCoContent(commentBase.getCoContent());
        commentBaseRepository.save(commentBase);
        return result;
    }

    @Override
    public int commentRemove(Integer coNum) throws CommentNotFoundException {
        // TODO : 본인 체크
        int result = 1;
        CommentBase commentBase = commentBaseRepository.findByCoNumAndCoDel(coNum,COMMENT_NOT_DEL).orElseThrow(CommentNotFoundException::new);
        commentBase.setCoDel('Y');
        commentBaseRepository.save(commentBase);
        return result;
    }


}
