package com.flipzon.service;

import java.util.List;

import com.flipzon.model.Customer;

public interface CustomerService {
  
	

	String addCustomer(Customer customer);

	List<Customer> findAll();
	   
}
