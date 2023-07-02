package com.flipzon.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flipzon.utils.ErrorDetails;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice   // global level exception handling
public class MyControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
		int count=1;
		for (ObjectError or : allErrors) {
			errors.put("field"+(count++), or.getDefaultMessage());
		}
		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleCustomerNotFoundException(CustomerNotFoundException exception) {
		ErrorDetails error = new ErrorDetails(HttpStatus.NOT_FOUND, exception.getMessage());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNofFoundException.class)
	public ResponseEntity<ErrorDetails> handleProductNofFoundException(ProductNofFoundException exception,HttpServletRequest request) {
		 
		ErrorDetails error = new ErrorDetails(HttpStatus.NOT_FOUND, exception.getMessage(),request.getRequestURI());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleException(Exception exception) {
		ErrorDetails error = new ErrorDetails(HttpStatus.NOT_FOUND, exception.getMessage());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}

}
