package com.flipzon.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
//@Table(name = "product_master")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pk;
	private String name;
	private String qty;
	private double price;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(columnDefinition = "product_type_pk")
	private ProductType productType;
	private String desc;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(columnDefinition = "customer_pk")
	private Customer customer;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Comment comment;
	@JsonIgnore
	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;
	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
}
