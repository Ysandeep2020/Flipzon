package com.flipzon.dto;

import lombok.Data;

@Data
public class CommentRequest {
	private String comment;
	private int rating;
	private Long productPk;
}
