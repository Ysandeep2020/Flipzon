package com.flipzon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipzon.model.Comment;
import com.flipzon.model.Customer;
import com.flipzon.model.Product;
import com.flipzon.model.ProductType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByPk(long pk);

	List<Product> findAllByCustomerEmail(String email);
}
