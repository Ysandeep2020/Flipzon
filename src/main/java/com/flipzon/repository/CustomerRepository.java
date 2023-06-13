package com.flipzon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipzon.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// select * from customer where pk=1;
	// select * from customer where name="";

	Customer findByPk(long pk);

	Customer findByName(String name);

	Customer findByMobile(String mobile);
	
	Customer findByEmail(String email);

}
