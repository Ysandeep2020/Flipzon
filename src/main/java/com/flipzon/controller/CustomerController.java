package com.flipzon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flipzon.dto.CustomerRequest;
import com.flipzon.model.Customer;
import com.flipzon.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody CustomerRequest customerRequest) {
		String output = customerService.addCustomer(customerRequest);
		return ResponseEntity.ok(output);
	}

	@GetMapping
	public ResponseEntity<List<Customer>> findAll() {
		return ResponseEntity.ok(customerService.findAll());
	}

	@GetMapping("/{pk}")
	public ResponseEntity<Customer> getCustomerByPk(@PathVariable long pk) {
		Customer customer = customerService.findByPk(pk);
		return ResponseEntity.ok(customer);
	}
	
	@GetMapping("/byPk")
	public ResponseEntity<Customer> getCustomerByPkOfRquestPaream(@RequestParam("pk") long pk) {
		Customer customer = customerService.findByPk(pk);
		return ResponseEntity.ok(customer);
	}
}
