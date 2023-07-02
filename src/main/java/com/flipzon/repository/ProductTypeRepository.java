package com.flipzon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipzon.model.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

	Optional<ProductType> findByPk(long pk);
   
	 
}
