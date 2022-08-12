package com.example.spring.boot.jdbc.h2.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
	
	private Object data;
	private String message;
	
	public ResponseDTO() {
		super();
	}

	public ResponseDTO(Object data, String message) {
		super();
		this.data = data;
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
		
}
