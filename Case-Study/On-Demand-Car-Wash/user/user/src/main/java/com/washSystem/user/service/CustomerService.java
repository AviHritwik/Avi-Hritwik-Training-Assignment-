package com.washSystem.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.washSystem.user.model.Address;
import com.washSystem.user.model.CardDetail;
import com.washSystem.user.model.Cars;
import com.washSystem.user.model.Customer;
import com.washSystem.user.model.Washer;
import com.washSystem.user.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository repo;
	
	
	public long saveUser(Customer customer) {
		customer.setStatus(true);
		repo.save(customer);
		return customer.getCustomerID();
	}
	
	public List<Customer> getCustomers() throws Exception{
		List<Customer> customerList= repo.findAll();
		if(customerList.isEmpty())
			throw new Exception();
		return customerList;
	}
	
	public Customer getCustomer(Long id) throws IllegalArgumentException,Exception {
		Customer customer = repo.findById(id).orElseThrow();
		
		return customer;
	}

	public void deleteCustomer(Long id) throws IllegalArgumentException,Exception {
		Customer customer = repo.findById(id).orElseThrow();
		repo.deleteById(id);
	}
	
	public Customer updateCustomer(long id,Customer updatedCustomer) throws IllegalArgumentException,Exception{
		Customer customer = repo.findById(id).orElseThrow(()-> new Exception());
		customer.setCustomerEmail(updatedCustomer.getCustomerEmail());
		customer.setCustomerID(updatedCustomer.getCustomerID());
		customer.setCustomerName(updatedCustomer.getCustomerName());
    	customer.setCustomerAddress(updatedCustomer.getCustomerAddress());
    	customer.setCardDetails(updatedCustomer.getCardDetails());
    	customer.setCars(updatedCustomer.getCars());
        final Customer newCustomer = repo.save(customer);
    	return newCustomer;
    }
	
	public void addAddress(long id, Address address) throws IllegalArgumentException,Exception
	{
		Customer customer = repo.findById(id).orElseThrow(()-> new Exception());
		if(customer.getCustomerAddress()== null)
			customer.setCustomerAddress(new ArrayList<Address>());
		List<Address> adressList = customer.getCustomerAddress();
		adressList.add(address);
		repo.save(customer);
	}
	
	public void addCar(long id, Cars car) throws IllegalArgumentException,Exception
	{
		Customer customer = repo.findById(id).orElseThrow(()-> new Exception());
		if(customer.getCars()== null)
			customer.setCars(new ArrayList<Cars>());
		List<Cars> cars = customer.getCars();
		cars.add(car);
		repo.save(customer);
	}
	
	public void addCards(long id, CardDetail card) throws IllegalArgumentException,Exception
	{
		Customer customer = repo.findById(id).orElseThrow(()-> new Exception());
		if(customer.getCardDetails()== null)
			customer.setCardDetails(new ArrayList<CardDetail>());
		List<CardDetail> cards = customer.getCardDetails();
		cards.add(card);
		repo.save(customer);
	}
	
	public ResponseEntity<?> changeStatus(long id) throws Exception{
		Customer customer = repo.findById(id).orElseThrow(()-> new Exception());
		if(customer.getStatus())
			customer.setStatus(false);
		else
			customer.setStatus(true);
		repo.save(customer);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
