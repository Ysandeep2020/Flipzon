package com.flipzon.config;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.flipzon.model.Customer;
import com.flipzon.repository.CustomerRepository;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByName(username);
		return new MyUserDetails(customer);
	}

}
