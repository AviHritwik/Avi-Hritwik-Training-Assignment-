package com.washSystem.user.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Washer")
public class Washer {
	

	@Id
	private long washerId;
	@NotBlank(message = "Email can't be blank")
	@Email
	private String email;
	@NotBlank(message = "Name can't be blank")
	@Size(min=3)
	private String name;
	@Valid
	private Address washerAddress;
	@NotNull
	private boolean status;
	

	public Washer() {}
	
	public Washer(long washerId, @NotBlank(message = "Email can't be blank") @Email String email,
			@NotBlank(message = "Name can't be blank") @Size(min = 3) String name, @NotNull Address washerAddress,
			@NotNull boolean status) {
		super();
		this.washerId = washerId;
		this.email = email;
		this.name = name;
		this.washerAddress = washerAddress;
		this.status = status;
	}


	public long getWasherId() {
		return washerId;
	}

	public void setWasherId(long washerId) {
		this.washerId = washerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getWasherAddress() {
		return washerAddress;
	}

	public void setWasherAddress(Address washerAddress) {
		this.washerAddress = washerAddress;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	
}
