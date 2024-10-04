package com.mogumogu.moru.board.repository;

import com.mogumogu.moru.board.entity.BoardFeedback;
import com.mogumogu.moru.board.entity.BoardNotice;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardFeedbackRepository extends BoardBaseRepository<BoardFeedback,Integer>{
}
