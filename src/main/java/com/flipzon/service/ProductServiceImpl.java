package com.flipzon.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.flipzon.advice.CustomerNotFoundException;
import com.flipzon.advice.ProductNofFoundException;
import com.flipzon.advice.ProductTypeNofFoundException;
import com.flipzon.dto.ProductRequest;
import com.flipzon.model.Customer;
import com.flipzon.model.Product;
import com.flipzon.model.ProductType;
import com.flipzon.repository.CustomerRepository;
import com.flipzon.repository.ProductRepository;
import com.flipzon.repository.ProductTypeRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductTypeRepository productTypeRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Product addProduct(ProductRequest productRequest) {
		// validation
		Product product = new Product();
		Long productTypePk = productRequest.getProductTypePk();
		ProductType productType = productTypeRepository.findByPk(productTypePk)
				.orElseThrow(() -> new ProductTypeNofFoundException("ProductType not found with pk " + productTypePk));
		product.setProductType(productType);
		Long customerPk = productRequest.getCustomerPk();
		Customer customer = customerRepository.findByPk(customerPk)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not found with pk " + customerPk));
		product.setCustomer(customer);
		BeanUtils.copyProperties(productRequest, product);
		return productRepository.save(product);
	}

	@Override
	public Map<String , Object> getAllProducts(int page, String prop, String order) {
		 Map<String , Object> map=new HashMap<>();
		// List<Product> findAll = productRepository.findAll();
		PageRequest pageable = null;
		if (order.contains("desc"))
			pageable = PageRequest.of(page - 1, 2, Sort.by(Order.desc(prop)));
	//	pageable = PageRequest.of(page - 1, 2, Sort.by(Order.));
		   
		else
			pageable = PageRequest.of(page - 1, 2, Sort.by(Order.asc(prop)));

		Page<Product> all = productRepository.findAll(pageable);
		  map.put("content", all.getContent());
		  map.put("totalPages", all.getTotalPages());
		return map;
	}

	@Override
	public Product findByPk(long pk) {
		Optional<Product> optProduct = productRepository.findByPk(pk);
		// Product procut =productRepository.findByPk(pk); //NullPointerExcepion handle
		// product validation

		if (!optProduct.isPresent()) {
			// throw exception
		}
		Product product = optProduct.get();
		return productRepository.findByPk(pk)
				.orElseThrow(() -> new ProductNofFoundException("Product Not found with Pk " + pk));
	}

	@Override
	public Void deleteProductByPk(long pk) {
		productRepository.deleteById(pk);
		return null;
	}

	@Override
	public Product updateProduct(long pk, ProductRequest productRequest) {
		Product existingProduct = productRepository.findByPk(pk)
				.orElseThrow(() -> new ProductNofFoundException("Product Not found with Pk " + pk));

		BeanUtils.copyProperties(productRequest, existingProduct);
		return productRepository.save(existingProduct);
	}

	@Override
	public Product partialProductUpdate(long pk, ProductRequest productRequest) {
		Product existingProduct = productRepository.findByPk(pk)
				.orElseThrow(() -> new ProductNofFoundException("Product Not found with Pk " + pk));

		if (StringUtils.isNotBlank(productRequest.getName())) {
			existingProduct.setName(productRequest.getName());
		}
		if (StringUtils.isNotBlank(productRequest.getDesc())) {
			existingProduct.setDesc(productRequest.getDesc());
		}
//		if (StringUtils.isNotBlank(productRequest.getProductType())) {
//			// TODO
//			// existingProduct.setProductType(productRequest.getProductType());
//		}
		if (StringUtils.isNotBlank(productRequest.getQty())) {
			existingProduct.setQty(productRequest.getQty());
		}
		if (productRequest.getPrice() != 0 && existingProduct.getPrice() != productRequest.getPrice()) {
			existingProduct.setPrice(productRequest.getPrice());
		}
		return productRepository.save(existingProduct);
	}

}
