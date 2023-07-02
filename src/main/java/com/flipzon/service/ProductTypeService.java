package com.flipzon.service;

import java.util.List;

import com.flipzon.dto.ProductTypeRequest;
import com.flipzon.model.ProductType;

import jakarta.validation.Valid;

public interface ProductTypeService {

	public ProductType addProductType(@Valid ProductTypeRequest productType);

	public List<ProductType> getAllProductTypes();

	public ProductType findByPk(long pk);

	public Void deleteProductTypeByPk(long pk);

	public ProductType updateProductType(long pk, ProductTypeRequest productTypeRequest);

	public ProductType partialProductTypeUpdate(long pk, ProductTypeRequest productTypeRequest);

}
