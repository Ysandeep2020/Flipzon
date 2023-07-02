package com.flipzon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flipzon.constents.UrlMappings;
import com.flipzon.dto.CommentRequest;
import com.flipzon.model.Comment;
import com.flipzon.model.Product;
import com.flipzon.repository.CommentRepository;
import com.flipzon.repository.ProductRepository;
import com.flipzon.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping
	public ResponseEntity<Comment> addComment(@Valid @RequestBody CommentRequest commentRequest) {
		Comment comment = commentService.addComment(commentRequest);
		return new ResponseEntity<Comment>(comment, HttpStatus.CREATED); // 201 created
	}

	@GetMapping("/product/{pk}")
	public ResponseEntity<Comment> getCommentByProductPk(@PathVariable long pk) {
		Comment comment = commentService.getCommentByProductPk(pk);
		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Comment>> allComment() {
		return new ResponseEntity<List<Comment>>(commentService.findAll(), HttpStatus.OK);
	}

	@DeleteMapping("/{pk}")
	public ResponseEntity<String> deleteCommentByPk(@PathVariable Long pk) {
		return ResponseEntity.ok(commentService.deleteCommentByPk(pk));
	}

	/**
	 * @GetMapping public ResponseEntity<List<ProductType>> getAllProductsTypes() {
	 *             List<ProductType> allProductTypes =
	 *             productTypeService.getAllProductTypes(); return new
	 *             ResponseEntity<List<ProductType>>(allProductTypes,
	 *             HttpStatus.OK);// 200 OK }
	 * 
	 * @GetMapping(UrlMappings.PK) public ResponseEntity<ProductType>
	 *                             getProductTypeByPk(@PathVariable long pk) {
	 *                             ProductType productType =
	 *                             productTypeService.findByPk(pk); return new
	 *                             ResponseEntity<ProductType>(productType,
	 *                             HttpStatus.OK); }
	 * 
	 * @DeleteMapping public ResponseEntity<Void>
	 *                deleteProductTypeByPk(@RequestParam("pk") long pk) { return
	 *                ResponseEntity.ok(productTypeService.deleteProductTypeByPk(pk));
	 *                }
	 * 
	 * @PutMapping(UrlMappings.PK) public ResponseEntity<Object>
	 *                             updateProductType(@RequestBody ProductTypeRequest
	 *                             productTypeRequest,
	 * @PathVariable long pk) { ProductType productType =
	 *               productTypeService.updateProductType(pk, productTypeRequest);
	 *               return new ResponseEntity<Object>(productType, HttpStatus.OK);
	 *               }
	 * 
	 * @PatchMapping(UrlMappings.PK) public ResponseEntity<Object>
	 *                               partialProductTypeUpdate(@RequestBody
	 *                               ProductTypeRequest productTypeRequest,
	 * @PathVariable long pk) { ProductType productType =
	 *               productTypeService.partialProductTypeUpdate(pk,
	 *               productTypeRequest); return new
	 *               ResponseEntity<Object>(productType, HttpStatus.OK); }
	 * 
	 **/
}
