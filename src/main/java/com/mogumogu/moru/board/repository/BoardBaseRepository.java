package com.mogumogu.moru.board.repository;

import com.mogumogu.moru.board.entity.BoardBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

public interface BoardBaseRepository<T extends BoardBase,ID extends Serializable> extends JpaRepository<T,ID> {

}
