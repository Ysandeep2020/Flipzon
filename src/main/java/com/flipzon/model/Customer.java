package com.flipzon.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
//@Table(name = "customer_detatils")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pk;
	// @Column(name = "customer_name")
	private String name;
	private String email;
	private String mobile;
	private String address;
	private String password;
	private String roles;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Product> products;

	@JsonIgnore
	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;
	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
}
