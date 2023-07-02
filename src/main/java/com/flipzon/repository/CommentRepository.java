package com.flipzon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipzon.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Comment findByProductPk(Long pk);
}
