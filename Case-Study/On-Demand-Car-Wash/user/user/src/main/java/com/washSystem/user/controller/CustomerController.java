package com.washSystem.user.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.washSystem.user.model.Address;
import com.washSystem.user.model.CardDetail;
import com.washSystem.user.model.Cars;
import com.washSystem.user.model.Customer;
import com.washSystem.user.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	//admin
	@GetMapping("/admin/findAll")
	public ResponseEntity<?> findAllCustomer()
	{
		try {
			return new ResponseEntity<List<Customer>>(customerService.getCustomers(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Customer>>(new ArrayList<Customer>(),HttpStatus.NOT_FOUND);
		}
	}
	
	//admin user
	@GetMapping("/user/find/{id}")
	public ResponseEntity<?> findCustomer(@PathVariable("id") long id)
	{
		try {
			return new ResponseEntity<Customer>(customerService.getCustomer(id),HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception E) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user/findCar/{id}")
	public ResponseEntity<?> findCustomerCar(@PathVariable("id") long id)
	{
		try {
			Customer customer = customerService.getCustomer(id);
			return new ResponseEntity<List<Cars>>(customer.getCars(),HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception E) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/user/findCard/{id}")
	public ResponseEntity<?> findCustomerCard(@PathVariable("id") long id)
	{
		try {
			Customer customer = customerService.getCustomer(id);
			return new ResponseEntity<List<CardDetail>>(customer.getCardDetails(),HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception E) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@GetMapping("/user/findAddress/{id}")
	public ResponseEntity<?> findCustomerAddress1(@PathVariable("id") long id)
	{
		try {
			Customer customer = customerService.getCustomer(id);
			return new ResponseEntity<List<Address>>(customer.getCustomerAddress(),HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception E) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	//admin
	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") long id)
	{
		try {
			customerService.deleteCustomer(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception E) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	//user admin
	@PutMapping("/user/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable(value = "id") Long id,
		      @Valid @RequestBody Customer customer) {
		try {
			return new ResponseEntity<Customer>(customerService.updateCustomer(id, customer),HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	//user admin
	@PostMapping("/user/add")
	public ResponseEntity<Long> addCustomer(@Valid @RequestBody Customer customer)
	{
		return new ResponseEntity<Long>(customerService.saveUser(customer),HttpStatus.OK);
	}
	
	@PostMapping("/user/addAddress/{id}")
	public ResponseEntity<?> addAddress(@PathVariable("id") long id ,@Valid @RequestBody Address address){
		try {
			customerService.addAddress(id, address);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/user/addCar/{id}")
	public ResponseEntity<?> addCar(@PathVariable("id") long id ,@Valid @RequestBody Cars car){
		try {
			customerService.addCar(id, car);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/user/addCard/{id}")
	public ResponseEntity<?> addCard(@PathVariable("id") long id ,@Valid @RequestBody CardDetail card){
		try {
			customerService.addCards(id, card);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//admin
	@PutMapping("/admin/updateStatus/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable("id") long id){
		try{
			return customerService.changeStatus(id);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
