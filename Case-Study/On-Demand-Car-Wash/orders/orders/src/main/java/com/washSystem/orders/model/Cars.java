package com.washSystem.orders.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Cars {
	
	@NotBlank(message="Car name is blank")
	private String name;
	@NotBlank(message="Brand is blank")
	private String brand;
	@NotBlank(message="Car type is blank")
	@Pattern(regexp="^suv|car|midSuv",message="Invalid Payment Mode")
	private String type;
	@NotBlank(message="No of seat is blank")
	private String seat;
	
	
	
	public Cars() {
	}
	public Cars( String name,  String brand,  String type,  String seat) {
		this.name = name;
		this.brand = brand;
		this.type = type;
		this.seat = seat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	
	
	
}
