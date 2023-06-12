package com.flipzon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipzon.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
