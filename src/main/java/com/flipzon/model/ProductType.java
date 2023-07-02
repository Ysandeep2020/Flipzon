package com.flipzon.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_type")
public class ProductType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pk;
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "productType")
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
