package com.flipzon.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.flipzon.model.Customer;

public class MyUserDetails implements UserDetails {
	private String name;
	private String password;
	private List<GrantedAuthority> authorites;

	public MyUserDetails(Customer customer) {
		this.name = customer.getName();
		this.password = customer.getPassword();
		// String roles="AMDIN,MANAGER,USER";
		// String [] listOfRoles=
		// zF this.authorites =
//				Arrays.stream(customer.getRoles().split(","))
//				// .map(SimpleGrantedAuthority::new)
//				//.map(role -> new SimpleGrantedAuthority(role))
//				.collect(Collectors.toList());

		String[] roleArray = customer.getRoles().split(",");
		List<GrantedAuthority> auths = new ArrayList<>();
		for (String role : roleArray) {
			var sga = new SimpleGrantedAuthority(role);
			auths.add(sga);
		}
		this.authorites = auths;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorites;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
