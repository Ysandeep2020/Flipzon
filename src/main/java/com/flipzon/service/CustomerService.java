package com.flipzon.service;

import java.util.List;

import com.flipzon.dto.CustomerRequest;
import com.flipzon.model.Customer;

public interface CustomerService {

	String addCustomer(CustomerRequest customerRequest);

	List<Customer> findAll();

	Customer findByPk(long pk);

}
