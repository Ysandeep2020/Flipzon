package com.flipzon.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.flipzon.dto.CustomerRequest;
import com.flipzon.dto.ProductRequest;
import com.flipzon.model.Product;

public interface ProductService {

	Product addProduct(ProductRequest product);

	List<Product> getAllProducts();

	Product findByPk(long pk);

	Void deleteProductByPk(long pk);

	Product updateProduct(long pk, ProductRequest productRequest);

	Product partialProductUpdate(long pk, ProductRequest productRequest);

}
