package com.flipzon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {
	@NotBlank(message = "name is mandatory")
	@Size(min = 2, message = "name must contain 2 digits AtLeast")
	private String name;
	@NotBlank(message = "quantity is mandatory")
	private String qty;
	@NotNull(message = "price not be null value")
	private double price;
	@NotBlank(message = "desc is mandatory")
	private String desc;
	@NotNull
	private Long productTypePk;
	@NotNull
	private Long customerPk;
	
}
