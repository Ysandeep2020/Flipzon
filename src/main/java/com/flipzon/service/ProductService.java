package com.flipzon.service;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.flipzon.dto.CustomerRequest;
import com.flipzon.dto.ProductRequest;
import com.flipzon.model.Product;

public interface ProductService {

	Product addProduct(ProductRequest product);

	Map<String, Object> getAllProducts(int page, String prop, String order, Principal principal);

	Product findByPk(long pk);

	Void deleteProductByPk(long pk);

	Product updateProduct(long pk, ProductRequest productRequest);

	Product partialProductUpdate(long pk, ProductRequest productRequest);

	List<Product> getAllProducts(Principal principal);

}
