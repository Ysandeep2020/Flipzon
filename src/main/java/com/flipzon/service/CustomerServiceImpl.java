package com.flipzon.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipzon.dto.CustomerRequest;
import com.flipzon.model.Customer;
import com.flipzon.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String addCustomer(CustomerRequest customerRequest) {
		Customer customer = new Customer();
//		customer.setAddress(customerRequest.getAddress());
//		customer.setEmail(customerRequest.getEmail());
//		customer.setMobile(customerRequest.getMobile());
//		customer.setName(customerRequest.getName());
		BeanUtils.copyProperties(customerRequest, customer);
		customerRepository.save(customer);
		return customer.getName() + " Added!";
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findByPk(long pk) {
		return customerRepository.findByPk(pk);
	}

}
