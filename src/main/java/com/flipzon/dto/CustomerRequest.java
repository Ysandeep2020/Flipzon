package com.flipzon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequest {
	@NotBlank(message = "name is mandatory")
	@Size(min = 2,message = "name must be contain 2 digits")
	private String name;
	@NotBlank(message = "email is mandatory")
	@Email(message = "email format is invalid")
	private String email;
	@NotBlank(message = "mobile is mandatory")
	@Size(min = 10,max = 10 ,message = "mobile number must be 10 digits.")
	private String mobile;
	@NotBlank(message = "address is mandatory")
	private String address;
	@NotBlank(message = "password is mandatory")
	private String password;
	@NotBlank(message = "roles is mandatory")
	private String roles;
}
