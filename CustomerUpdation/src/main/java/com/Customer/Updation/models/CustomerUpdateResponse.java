package com.Customer.Updation.models;

import org.springframework.stereotype.Component;

@Component
public class CustomerUpdateResponse {
	private String message;
	private long id;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
