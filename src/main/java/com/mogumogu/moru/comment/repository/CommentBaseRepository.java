package com.mogumogu.moru.comment.repository;

import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.comment.entity.CommentBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Comment;
import java.util.Optional;

@Repository
public interface CommentBaseRepository extends JpaRepository<CommentBase, Integer> {
    Page<CommentBase> findByCoDelAndBoardBase(char coDel, BoardBase boardBase, Pageable pageable);

    Optional<CommentBase> findByCoNumAndCoDel(Integer coNum, char commentNotDel);
}
