package com.flipzon.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flipzon.advice.ProductNofFoundException;
import com.flipzon.dto.CommentRequest;
import com.flipzon.model.Comment;
import com.flipzon.model.Product;
import com.flipzon.repository.CommentRepository;
import com.flipzon.repository.ProductRepository;

import jakarta.validation.Valid;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Comment addComment(@Valid CommentRequest commentRequest) {
		Comment comment = new Comment();
		Product product = productRepository.findByPk(commentRequest.getProductPk()).orElseThrow(
				() -> new ProductNofFoundException("Product Not found with Pk " + commentRequest.getProductPk()));
		comment.setProduct(product);
		BeanUtils.copyProperties(commentRequest, comment);
		return commentRepository.save(comment);
	}

	@Override
	public Comment getCommentByProductPk(long pk) {
		return commentRepository.findByProductPk(pk);
	}

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public String deleteCommentByPk(Long pk) {
		commentRepository.deleteById(pk);
		return "Comment deleted !";
	}

}
