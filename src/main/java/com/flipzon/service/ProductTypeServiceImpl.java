package com.flipzon.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipzon.advice.ProductTypeNofFoundException;
import com.flipzon.dto.ProductTypeRequest;
import com.flipzon.model.ProductType;
import com.flipzon.repository.ProductTypeRepository;

import io.micrometer.common.util.StringUtils;

//@Slf4j
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired
	private ProductTypeRepository productTypeRepository;

	Logger log = LoggerFactory.getLogger(ProductTypeServiceImpl.class);
	//@Slf4j
	@Override
	public ProductType addProductType(ProductTypeRequest productTypeRequest) {
		log.info("ProductTypeServiceImpl Request {}", productTypeRequest);
		// seriveImp.method();
		System.out.println(1/0);

		ProductType productType = new ProductType();
		BeanUtils.copyProperties(productTypeRequest, productType);
		ProductType save = productTypeRepository.save(productType);

		// System.out.println(1/0);
		// log.debug("");
		//log.warn(" please take correct product Type");
		log.info("Response again {}", save.getPk());
		return save;
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
