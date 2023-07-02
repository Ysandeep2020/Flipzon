package com.flipzon.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.flipzon.dto.CommentRequest;
import com.flipzon.model.Comment;

import jakarta.validation.Valid;

public interface CommentService {

	Comment addComment(@Valid CommentRequest commentRequest);

	Comment getCommentByProductPk(long pk);

	List<Comment> findAll();

	String deleteCommentByPk(Long pk);

}
