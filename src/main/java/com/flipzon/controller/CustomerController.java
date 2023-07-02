package com.flipzon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flipzon.constents.UrlMappings;
import com.flipzon.dto.CustomerRequest;
import com.flipzon.model.Customer;
import com.flipzon.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(UrlMappings.CUSTOMERS)
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping
	public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
		String output = customerService.addCustomer(customerRequest);
		return new ResponseEntity<String>(output, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Customer>> findAll() {
		return ResponseEntity.ok(customerService.findAll());
	}

	@GetMapping(UrlMappings.PK)
	public ResponseEntity<Object> getCustomerByPk(@PathVariable long pk, HttpServletRequest request) {
		Customer customer = customerService.findByPk(pk);
		return new ResponseEntity<Object>(customer, HttpStatus.OK);
	}

	@GetMapping(UrlMappings.BY_PK)
	public ResponseEntity<Object> getCustomerByPkOfRquestPaream(@RequestParam("pk") long pk) throws Exception {
		Customer customer = customerService.findByPk(pk);
		return ResponseEntity.ok(customer);
	}

	@GetMapping(UrlMappings.BY_MOBILE_NO)
	public ResponseEntity<Customer> getCustomerByMobile(@RequestParam("mobile") String mobile) {
		Customer customer = null;
		try {
			customer = customerService.getCustomerByMobile(mobile);
		} catch (Exception e) {
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(customer);
	}

	@GetMapping(UrlMappings.BY_EMAIL)
	public ResponseEntity<Customer> getCustomerByEmail(@RequestParam("email") String email) {
		Customer customer = null;
		try {
			customer = customerService.getCustomerByEmail(email);
		} catch (Exception e) {
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(customer);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteCustomerByPk(@RequestParam("pk") long pk) {

		// return new
		// ResponseEntity<Void>(customerService.deleteCustomerByPk(pk),HttpStatus.OK);
		return ResponseEntity.ok(customerService.deleteCustomerByPk(pk));

	}

	@PutMapping(UrlMappings.PK)
	public ResponseEntity<Object> updateCustomer(@RequestBody CustomerRequest customerRequest, @PathVariable long pk) {
		Customer customer = customerService.updateCustomer(pk, customerRequest);
		return new ResponseEntity<Object>(customer, HttpStatus.OK);
	}

	@PatchMapping(UrlMappings.PK)
	public ResponseEntity<Object> updateCustomerProperty(@RequestBody CustomerRequest customerRequest,
			@PathVariable long pk) {
		Customer customer = customerService.updateCustomerProperty(customerRequest, pk);
		return new ResponseEntity<Object>(customer, HttpStatus.OK);
	}

	@GetMapping(UrlMappings.PRODUCTS_BY_CUSTOMER_PK)
	public ResponseEntity<Object> getProductsByCustomerPk(@RequestParam("pk") long pk) throws Exception {
		return ResponseEntity.ok(customerService.getProductsByCustomerPk(pk));
	}

}
