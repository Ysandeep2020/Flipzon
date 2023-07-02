package com.flipzon.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity

public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pk;
	private String comment;
	private int rating;
    @JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(columnDefinition = "product_pk")
	private Product product;

    @JsonIgnore
	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;
	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
}
