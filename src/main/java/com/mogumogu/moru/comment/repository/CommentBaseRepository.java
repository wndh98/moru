package com.mogumogu.moru.comment.repository;

import com.mogumogu.moru.comment.entity.CommentBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentBaseRepository extends JpaRepository<CommentBase, Integer> {
}
