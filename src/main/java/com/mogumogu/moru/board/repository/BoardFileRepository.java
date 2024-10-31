package com.mogumogu.moru.board.repository;

import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile, Integer> {
}
