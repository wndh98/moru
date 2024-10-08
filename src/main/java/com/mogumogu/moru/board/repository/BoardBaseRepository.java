package com.mogumogu.moru.board.repository;

import com.mogumogu.moru.board.entity.BoardBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

public interface BoardBaseRepository extends JpaRepository<BoardBase, Integer> {
    Page<BoardBase> findByBoType(String boType, Pageable pageable);

    Page<BoardBase> findByBoTypeAndBoTitleContaining(String boType, String boTitle, Pageable pageable);

    Page<BoardBase> findByBoTypeAndBoContentContaining(String boType, String boContent, Pageable pageable);

    Page<BoardBase> findByBoTypeAndBoTitleOrBoContentContaining(String boType, String boTitle, String boContent, Pageable pageable);

}
