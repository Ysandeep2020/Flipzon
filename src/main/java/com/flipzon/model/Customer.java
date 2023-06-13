package com.flipzon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
