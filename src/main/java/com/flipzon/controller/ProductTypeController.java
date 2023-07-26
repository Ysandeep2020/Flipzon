package com.flipzon.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.flipzon.dto.ProductTypeRequest;
import com.flipzon.model.ProductType;
import com.flipzon.service.ProductTypeService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(UrlMappings.PRODUCT_TYPES)
public class ProductTypeController {
	@Autowired
	private ProductTypeService productTypeService;

	@PostMapping
	public ResponseEntity<ProductType> addProductType(@Valid @RequestBody ProductTypeRequest productType) {
		log.info("ProductTypeController Request {}", productType);
		ProductType productOut = productTypeService.addProductType(productType);
		log.info("ProductTypeController Response {}", productType);
		return new ResponseEntity<ProductType>(productOut, HttpStatus.CREATED); // 201 created
	}

	@GetMapping
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	//@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<List<ProductType>> getAllProductsTypes() {
		List<ProductType> allProductTypes = productTypeService.getAllProductTypes();
		return new ResponseEntity<List<ProductType>>(allProductTypes, HttpStatus.OK);// 200 OK
	}

	@GetMapping(UrlMappings.PK)
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	@Secured({"ROLE_USER" })
	public ResponseEntity<ProductType> getProductTypeByPk(@PathVariable long pk) {
		ProductType productType = productTypeService.findByPk(pk);
		return new ResponseEntity<ProductType>(productType, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteProductTypeByPk(@RequestParam("pk") long pk) {
		return ResponseEntity.ok(productTypeService.deleteProductTypeByPk(pk));
	}

	@PutMapping(UrlMappings.PK)
	public ResponseEntity<Object> updateProductType(@RequestBody ProductTypeRequest productTypeRequest,
			@PathVariable long pk) {
		ProductType productType = productTypeService.updateProductType(pk, productTypeRequest);
		return new ResponseEntity<Object>(productType, HttpStatus.OK);
	}

	@PatchMapping(UrlMappings.PK)
	public ResponseEntity<Object> partialProductTypeUpdate(@RequestBody ProductTypeRequest productTypeRequest,
			@PathVariable long pk) {
		ProductType productType = productTypeService.partialProductTypeUpdate(pk, productTypeRequest);
		return new ResponseEntity<Object>(productType, HttpStatus.OK);
	}
}
