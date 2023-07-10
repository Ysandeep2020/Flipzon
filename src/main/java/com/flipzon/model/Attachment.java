package com.flipzon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
@Entity
//@Table(name = "")
public class Attachment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pk;
	private String name;
	private String type;
	@Lob
	private byte[] data;
	
	//private Product productId;
	// private String ;
	
	
	private String imgType;  //  customer   // product
	
}
