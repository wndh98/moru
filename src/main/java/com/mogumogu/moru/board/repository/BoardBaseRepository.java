package com.mogumogu.moru.board.repository;

import com.mogumogu.moru.board.entity.BoardBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

public interface BoardBaseRepository extends JpaRepository<BoardBase, Integer> {
    Page<BoardBase> findByBoDelAndBoType(char boDel,String boType, Pageable pageable);

    Page<BoardBase> findByBoDelAndBoTypeAndBoTitleContaining(char boDel,String boType, String boTitle, Pageable pageable);

    Page<BoardBase> findByBoDelAndBoTypeAndBoContentContaining(char boDel,String boType, String boContent, Pageable pageable);

    Page<BoardBase> findByBoDelAndBoTypeAndBoTitleOrBoContentContaining(char boDel,String boType, String boTitle, String boContent, Pageable pageable);
    Optional<BoardBase> findByBoNumAndBoDel(Integer boNum, char boDel);
}
