package com.washSystem.user.controller;

import java.util.List;
import java.util.Optional;
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

import com.washSystem.user.model.Customer;
import com.washSystem.user.model.Washer;
import com.washSystem.user.service.WasherService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/washer")
public class WasherController {
	
	@Autowired
	WasherService washerService;
	
	//admin
	@GetMapping("/admin/findAll")
	public ResponseEntity<?> findAllWasher(){
		try {
			
			return new ResponseEntity<List<Washer>>(washerService.findAllWasher(),HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	//washer admin
	@GetMapping("/washer/find/{id}")
	public ResponseEntity<Optional<Washer>> findWasher(@PathVariable("id") long id)
	{
		try {
			return new ResponseEntity(washerService.findWasher(id),HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		catch(Exception E) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	//admin
	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<Void> deleteWasher(@PathVariable("id") long id)
	{
		try {
			washerService.deleteWasher(id);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		catch(Exception E) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	//washer admin
	@PutMapping("/washer/update/{id}")
	public ResponseEntity<Washer> updateWasher(@PathVariable(value = "id") Long id,
		      @Valid @RequestBody Washer washer) {
		try {
			return new ResponseEntity(washerService.updateEmployee(id, washer),HttpStatus.OK);
		}
		catch(IllegalArgumentException i) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	//washer admin
	@PostMapping("/washer/add")
	public ResponseEntity<Long> addWasher(@Valid @RequestBody Washer washer)
	{
		return new ResponseEntity(washerService.saveWasher(washer),HttpStatus.OK);
	}
	
	//admin
	@PutMapping("/admin/updateStatus/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable("id") long id){
		try{
			return washerService.changeStatus(id);
		}
		catch(Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
