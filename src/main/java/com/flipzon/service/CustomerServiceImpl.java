package com.flipzon.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipzon.advice.CustomerNotFoundException;
import com.flipzon.dto.CustomerRequest;
import com.flipzon.model.Customer;
import com.flipzon.model.Product;
import com.flipzon.repository.CustomerRepository;
import com.flipzon.repository.ProductRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;

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
		return customerRepository.findByPk(pk)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not found With Pk " + pk));
	}

	@Override
	public Customer getCustomerByMobile(String mobile) {
		return customerRepository.findByMobile(mobile)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found With Mobile No"));
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not Found With Email Id"));

	}

	@Override
	public Void deleteCustomerByPk(long pk) {
		customerRepository.deleteById(pk);
		return null;
	}

	@Override
	public Customer updateCustomer(long pk, CustomerRequest customerRequest) {
		Optional<Customer> optCustomer = customerRepository.findByPk(pk);
		Customer existingCustomer = optCustomer.get();
		BeanUtils.copyProperties(customerRequest, existingCustomer);
		return customerRepository.save(existingCustomer);
	}

	@Override
	public Customer updateCustomerProperty(CustomerRequest customerRequest, long pk) {
		// partial updation
		Customer customer = customerRepository.findByPk(pk)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with Pk :" + pk));
		if (StringUtils.isNotBlank(customerRequest.getName())) {
			customer.setName(customerRequest.getName());
		}
		if (StringUtils.isNotBlank(customerRequest.getEmail())) {
			customer.setEmail(customerRequest.getEmail());
		}
		if (StringUtils.isNotBlank(customerRequest.getMobile())) {
			customer.setMobile(customerRequest.getMobile());
		}
		if (StringUtils.isNotBlank(customerRequest.getAddress())) {
			customer.setAddress(customerRequest.getAddress());
		}
		return customerRepository.save(customer);
	}

	@Override
	public List<Map<String, String>> getProductsByCustomerPk(long pk) {
		return customerRepository.findProductsByCustomerPk(pk);

	}

}
