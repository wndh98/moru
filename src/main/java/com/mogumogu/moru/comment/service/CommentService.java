package com.mogumogu.moru.comment.service;

import com.mogumogu.moru.comment.dto.CommentBaseDTO;
import com.mogumogu.moru.comment.exception.CommentNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    List<CommentBaseDTO> commentList(Integer boNum, Pageable pageable);

    int commentAdd(CommentBaseDTO commentBaseDTO);

    int commentModify(Integer coNum, CommentBaseDTO commentBaseDTO) throws CommentNotFoundException;

    int commentRemove(Integer coNum) throws CommentNotFoundException;
}
