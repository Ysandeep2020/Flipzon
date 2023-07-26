package com.flipzon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flipzon", version = "1.0", description = "Mainly focus on Products"))
public class FlipzonApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipzonApplication.class, args);
	}

}
