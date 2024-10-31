package com.mogumogu.moru.comment.controller;

import com.mogumogu.moru.board.exception.BoardNotFoundException;
import com.mogumogu.moru.comment.dto.CommentBaseDTO;
import com.mogumogu.moru.comment.exception.CommentNotFoundException;
import com.mogumogu.moru.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 게시판 번호 를 기준으로 댓글 List
     *
     * @param boNum    게시판번호
     * @param pageable 페이징
     * @return List<CommentBaseDTO> 댓글 리스트
     * @author 김주오
     */
    @GetMapping("/comment/{boNum}")
    public List<CommentBaseDTO> listComment(@PathVariable("boNum") Integer boNum, Pageable pageable) {
        List<CommentBaseDTO> list = null;
        list = commentService.commentList(boNum, pageable);
        return list;
    }

    /**
     * commentBaseDTO 를 기준으로 댓글 입력
     *
     * @param commentBaseDTO 댓글 내용
     * @return 1:성공 0 실패
     * @author 김주오
     */
    @PostMapping("/comment/{boNum}")
    public int addComment(@RequestBody CommentBaseDTO commentBaseDTO) {
        int result = 0;
        result = commentService.commentAdd(commentBaseDTO);
        return result;
    }
    /**
     * commentBaseDTO 를 기준으로 댓글 수정
     *
     * @param commentBaseDTO 댓글 수정 내용
     * @param coNum 댓글 번호 확인
     * @return 1:성공 0 실패
     * @author 김주오
     */
    @PutMapping("/comment/{coNum}")
    public int modifyComment(@PathVariable("coNum") Integer coNum,@RequestBody CommentBaseDTO commentBaseDTO) {
        int result = 0;
        try {
            result = commentService.commentModify(coNum,commentBaseDTO);
        } catch (CommentNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    /**
     * coNum 를 기준으로 댓글 삭제
     *
     * @param coNum 댓글 번호
     * @return 1:성공 0 실패
     * @author 김주오
     */
    @DeleteMapping("/comment/{coNum}")
    public int removeBoard(@PathVariable("coNum") Integer coNum){
        int result = 0;
        try {
            result = commentService.commentRemove(coNum);
        } catch (CommentNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
