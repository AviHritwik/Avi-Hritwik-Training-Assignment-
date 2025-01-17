package com.washSystem.orders.model;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;


@Document(collection="Order")
public class Order {
	
	@Transient
    public static final String SEQUENCE_NAME = "order_sequence";
	
	@Id
	private long orderId;
	private long washerId;
	@Min(value = 1, message = "User ID Invalid")
	private long userId;
	private double amount;
	@Valid
	private Address address;
	@Pattern(regexp="^Pending|Washer Assigned|Completed|Cancelled",message="Invalid Status")
	@NotBlank(message = "Order Status can't be blank")
	private String status;
	@Pattern(regexp="^Cash|Card",message="Invalid Payment Mode")
	@NotBlank(message="Payment Mode is Must")
	private String paymentMode;
	@NotBlank(message="Payment Status cannot be blank")
	@Pattern(regexp="^Pending|Success|Failed",message="Invalid Status")
	private String paymentStatus;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date serviceDate;
	@Valid
	private Cars car;
	private CardDetail card;
	@NotNull
	private String washServicesName;
	private List<String> addOnName;
	
	public Order() {
		super();
	}
	
	public Order(long orderId, long washerId,  long userId,  double amount,  Address address,
			String status,  String paymentMode,  String paymentStatus,
			 Date serviceDate, String washServicesName,  List<String> addOnName) {
		super();
		this.orderId = orderId;
		this.washerId = washerId;
		this.userId = userId;
		this.amount = amount;
		this.address = address;
		this.status = status;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
		this.serviceDate = serviceDate;
		this.washServicesName = washServicesName;
		this.addOnName = addOnName;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getWasherId() {
		return washerId;
	}
	public void setWasherId(long washerId) {
		this.washerId = washerId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getWashServicesName() {
		return washServicesName;
	}
	public Cars getCar() {
		return car;
	}

	public void setCar(Cars car) {
		this.car = car;
	}

	public CardDetail getCard() {
		return card;
	}

	public void setCard(CardDetail card) {
		this.card = card;
	}

	public void setWashServicesName(String washServicesName) {
		this.washServicesName = washServicesName;
	}
	public List<String> getAddOnName() {
		return addOnName;
	}
	public void setAddOnName(List<String> addOnName) {
		this.addOnName = addOnName;
	}
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	
	
	
	
}
