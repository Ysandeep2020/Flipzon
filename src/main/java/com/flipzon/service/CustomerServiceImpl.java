package com.flipzon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipzon.model.Customer;
import com.flipzon.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String addCustomer(Customer customer) {
		customerRepository.save(customer);
		return customer.getName() + " Added!";
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

}
