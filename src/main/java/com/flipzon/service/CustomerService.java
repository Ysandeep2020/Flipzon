package com.flipzon.service;

import java.util.List;
import java.util.Map;

import com.flipzon.dto.CustomerRequest;
import com.flipzon.model.Customer;
import com.flipzon.model.Product;

public interface CustomerService {

	String addCustomer(CustomerRequest customerRequest);

	List<Customer> findAll();

	Customer findByPk(long pk);

	Customer getCustomerByMobile(String mobile);

	Customer getCustomerByEmail(String email);

	Void deleteCustomerByPk(long pk);

	Customer updateCustomer(long pk, CustomerRequest customerRequest);

	Customer updateCustomerProperty(CustomerRequest customerRequest, long pk);

	List<Map<String, String>>  getProductsByCustomerPk(long pk);

}
