package com.flipzon.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flipzon.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// select * from customer where pk=1;
	// select * from customer where name="";

	Optional<Customer> findByPk(long pk);

	Customer findByName(String name);

	Optional<Customer> findByMobile(String mobile);

	Optional<Customer> findByEmail(String email);

	void deleteByPk(long pk);

	@Query(nativeQuery = true, value = "select name from product p where  customer_pk =?1")
	List<Map<String, String>> findProductsByCustomerPk(long pk);

}
