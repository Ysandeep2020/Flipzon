package com.flipzon.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipzon.advice.ProductNofFoundException;
import com.flipzon.advice.ProductTypeNofFoundException;
import com.flipzon.dto.ProductTypeRequest;
import com.flipzon.model.Product;
import com.flipzon.model.ProductType;
import com.flipzon.repository.ProductTypeRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Override
	public ProductType addProductType(ProductTypeRequest productTypeRequest) {
		ProductType productType = new ProductType();
		BeanUtils.copyProperties(productTypeRequest, productType);
		return productTypeRepository.save(productType);
	}

	@Override
	public List<ProductType> getAllProductTypes() {
		return productTypeRepository.findAll();
	}

	@Override
	public ProductType findByPk(long pk) {
		return productTypeRepository.findByPk(pk)
				.orElseThrow(() -> new ProductTypeNofFoundException("ProductType Not found with Pk " + pk));

	}

	@Override
	public Void deleteProductTypeByPk(long pk) {
		productTypeRepository.deleteById(pk);
		return null;
	}

	@Override
	public ProductType updateProductType(long pk, ProductTypeRequest productTypeRequest) {
		ProductType existingProduct = productTypeRepository.findByPk(pk)
				.orElseThrow(() -> new ProductTypeNofFoundException("ProductType Not found with Pk " + pk));

		BeanUtils.copyProperties(productTypeRequest, existingProduct);
		return productTypeRepository.save(existingProduct);
	}

	@Override
	public ProductType partialProductTypeUpdate(long pk, ProductTypeRequest productTypeRequest) {
		ProductType existingProductType = productTypeRepository.findByPk(pk)
				.orElseThrow(() -> new ProductTypeNofFoundException("ProductType Not found with Pk " + pk));

		if (StringUtils.isNotBlank(productTypeRequest.getName())) {
			existingProductType.setName(productTypeRequest.getName());
		}
		return productTypeRepository.save(existingProductType);
	}

}
