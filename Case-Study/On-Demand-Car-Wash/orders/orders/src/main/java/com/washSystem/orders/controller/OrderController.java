package com.washSystem.orders.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.washSystem.orders.service.OrderService;
import com.washSystem.orders.model.Order;
import com.washSystem.orders.repository.OrdersRepository;

@CrossOrigin(origins = "*")
@RestController
public class OrderController {

	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrdersRepository repo;
	
	//admin
	@RequestMapping("/admin/findAllOrder")
	public List<Order> getOrders(){
		return orderService.getOrders();
	}
	
	//admin user washer
	@RequestMapping("/all/findOrder/{id}")
	@ResponseBody
	public ResponseEntity<Optional<Order>> getOrder(@PathVariable long id) {
		try {
			return new ResponseEntity(orderService.getOrder(id),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	//admin user
	@RequestMapping("/user/findOrderByUser/{id}")
	public ResponseEntity<List<Order>> findOrderByUserId(@PathVariable long id) {
		try {
			List<Order> orderList = orderService.findByUserId(id);
			return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
		}
	}
	
	//admin washer
	@RequestMapping("/washer/findOrderByWasher/{id}")
	@ResponseBody
	public ResponseEntity<List<Order>> findOrderByWasherId(@PathVariable long id) {
		try {
			List<Order> orderList = orderService.findByWasherId(id);
			return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
		}
	}
	
	//washer user admin
	@RequestMapping("/all/updateStatus")
	public ResponseEntity<String> updateStatus(@RequestParam("id") long orderId,@RequestParam("status") String status) {
		try {
			String message = orderService.updateOrderStatus(orderId, status);
			return new ResponseEntity<String>(message,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<String>("Not Found",HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	//washer, admin
	@RequestMapping("/washer/updateWasher")
	public ResponseEntity<Void> updateWasher(@RequestParam("id") long orderId,@RequestParam("washerId") long washerId) {
		try {
			orderService.updateWasher(orderId, washerId);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	//admin user
	@PostMapping("/user/addOrder")
	public ResponseEntity<String> saveOrder(@Valid @RequestBody Order order) {
		try{
			return new ResponseEntity<String>(orderService.saveOrder(order),HttpStatus.ACCEPTED);
			
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Wrong Data Entered",HttpStatus.BAD_REQUEST);
		}
	}
	
	/*@RequestMapping("/deleteAll")
	public void deleteAll() {
		repo.deleteAll();
	}*/
	
	//admin
	@RequestMapping("/admin/deleteOrder/{id}")
	public String deleteOrder(@PathVariable long id) {
		return orderService.deleteOrder(id);
		
	}
}
