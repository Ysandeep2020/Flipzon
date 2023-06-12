package com.flipzon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipzon.model.Customer;
import com.flipzon.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping
	public String addCustomer(@RequestBody Customer customer) {
		String output = customerService.addCustomer(customer);
		return output;
	}

	@GetMapping
	public List<Customer> findAll() {
		return customerService.findAll();
	}

}
