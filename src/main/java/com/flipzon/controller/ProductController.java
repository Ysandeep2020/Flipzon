package com.flipzon.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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
import com.flipzon.dto.ProductRequest;
import com.flipzon.model.Product;
import com.flipzon.repository.CustomerRepository;
import com.flipzon.repository.ProductRepository;
import com.flipzon.repository.ProductTypeRepository;
import com.flipzon.service.ProductService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(UrlMappings.PRODUCTS)
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductTypeRepository productTypeRepository;

	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequest product) {
		Product productOut = productService.addProduct(product);
		return new ResponseEntity<Product>(productOut, HttpStatus.CREATED); // 201 created
	}

	@GetMapping("/page/{page}/sortBy/{prop}")
//	@GetMapping("/all")
	public ResponseEntity<Object> getAllProducts(@PathVariable int page, @PathVariable String prop,
			@RequestParam(defaultValue = "asc") String order, Principal principal) {
		 Map<String, Object> map = productService.getAllProducts(page, prop, order,
		 principal);

		return new ResponseEntity<Object>(map, HttpStatus.OK);// 200 OK
	}

	@GetMapping("/all")
//	@GetMapping("/all")
	public ResponseEntity<Object> getAllProductsByUsername(Principal principal) {
		// Map<String, Object> map = productService.getAllProducts(page, prop, order,
		// principal);
		List<Product> all = productService.getAllProducts(principal);
		return new ResponseEntity<Object>(all, HttpStatus.OK);// 200 OK
	}

	@GetMapping(UrlMappings.PK)
	public ResponseEntity<Product> getProductByPk(@PathVariable long pk) {
		Product product = productService.findByPk(pk);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteProductByPk(@RequestParam("pk") long pk) {
		return ResponseEntity.ok(productService.deleteProductByPk(pk));
	}

	@PutMapping(UrlMappings.PK)
	public ResponseEntity<Object> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable long pk) {
		Product product = productService.updateProduct(pk, productRequest);
		return new ResponseEntity<Object>(product, HttpStatus.OK);
	}

	@PatchMapping(UrlMappings.PK)
	public ResponseEntity<Object> partialProductUpdate(@RequestBody ProductRequest productRequest,
			@PathVariable long pk) {
		Product product = productService.partialProductUpdate(pk, productRequest);
		return new ResponseEntity<Object>(product, HttpStatus.OK);
	}

	@PatchMapping("/customer/{email}")
	public ResponseEntity<Object> AllProductByCustomerEmail(@PathVariable String email) {
		List<Product> all = productRepository.findAllByCustomerEmail(email);
		return new ResponseEntity<Object>(all, HttpStatus.OK);
	}

}
