package com.flipzon.utils;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorDetails {

	public Timestamp timestamp;
	public int StatusCode;
	public HttpStatus status;
	public String message;
	public String path;

	public ErrorDetails(Timestamp timestamp, int statusCode, HttpStatus status, String message, String path) {
		super();
		this.timestamp = timestamp;
		StatusCode = statusCode;
		this.status = status;
		this.message = message;
		this.path = path;
	}

	public ErrorDetails(HttpStatus status, String message, String path) {
		this.status = status;
		this.message = message;
		this.path = path;
	}

	public ErrorDetails(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

}
